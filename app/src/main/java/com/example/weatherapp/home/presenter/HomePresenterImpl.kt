package com.example.weatherapp.home.presenter

import android.graphics.Bitmap
import android.location.Location
import androidx.lifecycle.LiveData
import com.example.weatherapp.base.repo.local.dto.HistoryImages
import com.example.weatherapp.home.model.HomeRepo
import com.example.weatherapp.home.view.contract.HomeContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(private val homeRepo: HomeRepo) :
    HomeContract.HomePresenter() {

    override fun handleOnTakingPhotoResult(
        lastLocation: Location,
        resultCode: Int,
        imageName: String?,
        data: Bitmap?
    ) {

        homeRepo.getRemoteWeatherDataAndSaveImage(
            data,
            imageName,
            lastLocation.latitude,
            lastLocation.longitude
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            view?.onGettingWeather(it)
        }

    }

    override fun getAllImages(): LiveData<List<HistoryImages>> {
        return homeRepo.getHistoryImages()
    }


}