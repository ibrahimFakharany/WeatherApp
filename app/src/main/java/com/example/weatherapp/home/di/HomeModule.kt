package com.example.weatherapp.home.di

import android.content.Context
import com.example.weatherapp.home.model.HomeRepo
import com.example.weatherapp.home.model.HomeRepoImpl
import com.example.weatherapp.home.model.cloud.HomeCloudRepo
import com.example.weatherapp.home.model.local.HomeLocalRepo
import com.example.weatherapp.home.model.local.HomeLocalRepoImpl
import com.example.weatherapp.home.presenter.HomePresenterImpl
import com.example.weatherapp.home.view.contract.HomeContract
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideHomePresenter(homeRepo: HomeRepo): HomeContract.HomePresenter =
        HomePresenterImpl(homeRepo)

    @Provides
    fun provideHomeRepo(
        homeCloudRepo: HomeCloudRepo,
        context: Context,
        homeLocalRepo: HomeLocalRepo
    ): HomeRepo =
        HomeRepoImpl(homeLocalRepo, homeCloudRepo, context)
}