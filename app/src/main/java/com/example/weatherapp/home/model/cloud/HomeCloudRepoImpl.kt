package com.example.weatherapp.home.model.cloud

import com.example.weatherapp.base.repo.cloud.ApiServices
import com.example.weatherapp.home.model.dto.WeatherResponse
import io.reactivex.Observable
import javax.inject.Inject

class HomeCloudRepoImpl @Inject constructor(val service: ApiServices) : HomeCloudRepo {

    override fun getWeatherData(
        lat: Double,
        lng: Double
    ): Observable<WeatherResponse> =
        service.getWeatherData(lat, lng)

}