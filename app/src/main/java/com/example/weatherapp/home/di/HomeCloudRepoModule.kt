package com.example.weatherapp.home.di

import com.example.weatherapp.base.repo.cloud.ApiServices
import com.example.weatherapp.home.model.cloud.HomeCloudRepo
import com.example.weatherapp.home.model.cloud.HomeCloudRepoImpl
import dagger.Module
import dagger.Provides


@Module
class HomeCloudRepoModule {

    @Provides
    fun provideHomeCloudRepo(apiServices: ApiServices): HomeCloudRepo =
        HomeCloudRepoImpl(apiServices)
}