package com.example.weatherapp.base.repo.cloud

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.home.model.dto.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Observable<WeatherResponse>
}