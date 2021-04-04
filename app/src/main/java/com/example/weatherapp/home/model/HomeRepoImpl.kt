package com.example.weatherapp.home.model

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.base.repo.local.dto.HistoryImages
import com.example.weatherapp.home.model.cloud.HomeCloudRepo
import com.example.weatherapp.home.model.local.HomeLocalRepo
import com.example.weatherapp.utils.ImageSaver
import com.example.weatherapp.utils.ImageUtil
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val localHistoryRepo: HomeLocalRepo,
    private val homeCloudRepo: HomeCloudRepo,
    private val context: Context
) : HomeRepo {
    override fun getRemoteWeatherDataAndSaveImage(
        bitmap: Bitmap?,
        imageName: String?,
        lat: Double,
        lng: Double
    ): Observable<Bitmap> {
        return homeCloudRepo.getWeatherData(lat, lng).subscribeOn(Schedulers.io()).flatMap {
            var img = ImageUtil.drawOnImageView(
                bitmap,
                it.name + "\n" + it.main?.temp + "\n" + it?.weather?.get(0)?.description
            )
            saveImgToDevice(img, imageName)
            imageName?.let { it1 -> Log.e("filename", it1) }
            localHistoryRepo.saveImg(
                imageName, ImageUtil.getByteFromImage(img)
            )
            Observable.just(img)
        }
    }

    private fun saveImgToDevice(img: Bitmap, imageName: String?) {
        ImageSaver(context).setFileName(imageName).setDirectoryName("images").save(img)
    }

    override fun getHistoryImages(): LiveData<List<HistoryImages>> {
        return localHistoryRepo.getAllImages()
    }
}