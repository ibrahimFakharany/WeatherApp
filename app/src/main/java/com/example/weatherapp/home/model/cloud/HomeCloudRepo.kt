package com.example.weatherapp.home.model.cloud

import com.example.weatherapp.home.model.dto.WeatherResponse
import io.reactivex.Observable

interface HomeCloudRepo {
    fun getWeatherData(lat: Double, lng: Double): Observable<WeatherResponse>
}