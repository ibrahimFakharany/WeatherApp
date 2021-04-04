package com.example.weatherapp.base.di

import com.example.weatherapp.base.repo.cloud.ApiServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class ServiceModule {

    @Provides
    fun provideService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

}