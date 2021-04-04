package com.example.weatherapp.home.view.contract

import android.graphics.Bitmap
import android.location.Location
import androidx.lifecycle.LiveData
import com.example.weatherapp.base.presenter.BasePresenter
import com.example.weatherapp.base.repo.local.dto.HistoryImages
import com.example.weatherapp.base.view.MvpView

interface HomeContract {
    interface HomeView : MvpView {
        fun onGettingWeather(bitmap: Bitmap?);
    }

    abstract class HomePresenter : BasePresenter<HomeView>() {
        abstract fun handleOnTakingPhotoResult(
            lastLocation: Location,
            resultCode: Int,
            imageName: String?,
            data: Bitmap?
        )

        abstract fun getAllImages(): LiveData<List<HistoryImages>>
    }
}