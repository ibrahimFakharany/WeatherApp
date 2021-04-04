package com.example.weatherapp.home.di

import com.example.weatherapp.base.di.AppDatabase
import com.example.weatherapp.base.repo.cloud.ApiServices
import com.example.weatherapp.home.model.cloud.HomeCloudRepo
import com.example.weatherapp.home.model.cloud.HomeCloudRepoImpl
import com.example.weatherapp.home.model.local.HomeLocalRepo
import com.example.weatherapp.home.model.local.HomeLocalRepoImpl
import dagger.Module
import dagger.Provides

@Module
class HomeLocalModule {

    @Provides
    fun provideHomeCloudRepo(db: AppDatabase): HomeLocalRepo =
        HomeLocalRepoImpl(db.historyDao())
}