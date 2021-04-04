package com.example.weatherapp.base.di

import com.example.weatherapp.home.di.HomeCloudRepoModule
import com.example.weatherapp.home.di.HomeLocalModule
import com.example.weatherapp.home.di.HomeModule
import com.example.weatherapp.home.model.cloud.HomeCloudRepo
import com.example.weatherapp.home.view.activity.HomeActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [CloudModule::class, DatabaseModule::class, ServiceModule::class,
        HomeCloudRepoModule::class, HomeLocalModule::class, HomeModule::class]
)
interface ApplicationComponent {
    fun inject(homeActivity: HomeActivity)
}