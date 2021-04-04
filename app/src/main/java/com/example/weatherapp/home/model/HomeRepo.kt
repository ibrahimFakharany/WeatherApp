package com.example.weatherapp.home.model

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.example.weatherapp.base.repo.local.dto.HistoryImages
import io.reactivex.Observable

interface HomeRepo {
    fun getRemoteWeatherDataAndSaveImage(
        bitmap: Bitmap?,
        imageName:String?,
        lat: Double,
        lng: Double
    ): Observable<Bitmap>

    fun getHistoryImages(): LiveData<List<HistoryImages>>
}