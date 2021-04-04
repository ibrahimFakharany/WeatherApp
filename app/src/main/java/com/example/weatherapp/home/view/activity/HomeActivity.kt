package com.example.weatherapp.home.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherapp.App
import com.example.weatherapp.Constants
import com.example.weatherapp.Constants.RC_CAMERA_LOCATION_PERM
import com.example.weatherapp.R
import com.example.weatherapp.base.view.MvpActivity
import com.example.weatherapp.detail.DetailActivity
import com.example.weatherapp.home.view.adapter.HistoryImagesAdapter
import com.example.weatherapp.home.view.contract.HomeContract
import com.example.weatherapp.home.view.contract.onImageClickedCallback
import com.example.weatherapp.utils.ImageUtil
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.share.Sharer
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.Signature
import kotlin.math.log


class HomeActivity : MvpActivity<HomeContract.HomeView, HomeContract.HomePresenter>(),
    HomeContract.HomeView, EasyPermissions.PermissionCallbacks {

    private val TAG: String? = HomeActivity::class.simpleName

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var locationCallback: LocationCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                lastLocation = p0.lastLocation
            }
        }

        takePicBtnHomeActiviy.setOnClickListener {
            checkPermission()
        }

        shareBtnHomeActivity.setOnClickListener {
            var bitmap = (imgPreview.drawable as BitmapDrawable).bitmap
            ImageUtil.shareImg(this, bitmap)
        }

        imagesRv.layoutManager = GridLayoutManager(this, 4)
        var historyAdapter = HistoryImagesAdapter(object : onImageClickedCallback {
            override fun onImageClicked(name: String?) {
                Intent(this@HomeActivity, DetailActivity::class.java).let { intent ->
                    intent.putExtra(Constants.IMAGE_INTENT_KEY, name)
                    startActivity(intent)
                }
            }
        })

        imagesRv.adapter = historyAdapter
        presenter.getAllImages().observe(this, Observer {

            recentImagesHomeActivity.visibility = View.VISIBLE
            historyAdapter.addData(it as ArrayList)
        })
    }

    private fun checkPermission() {
        if (EasyPermissions.hasPermissions(
                this, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            takePhoto();
        } else {

            EasyPermissions.requestPermissions(
                this,
                getString(R.string.explanations_perms),
                RC_CAMERA_LOCATION_PERM,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun takePhoto() {
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location

            }
        }
        ImagePicker.with(this).compress(1024).maxResultSize(1024, 1024)
            .start(RC_CAMERA_LOCATION_PERM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_CAMERA_LOCATION_PERM) {
            if (resultCode == Activity.RESULT_OK) {

                presenter.handleOnTakingPhotoResult(
                    lastLocation,
                    resultCode,
                    ImagePicker.getFile(data)?.name,
                    MediaStore.Images.Media.getBitmap(
                        this.contentResolver, data?.data
                    )
                )
            }
        }
    }

    override fun inject() {
        App.applicationComponent.inject(this@HomeActivity)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        checkPermission()
    }

    override fun onGettingWeather(bitmap: Bitmap?) {
        shareBtnHomeActivity.visibility = View.VISIBLE
        imgPreview.setImageBitmap(bitmap)
    }
}
