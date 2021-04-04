package com.example.weatherapp.base.di

import com.example.weatherapp.base.repo.cloud.CloudConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class CloudModule {

    @Provides
    fun provideOkHttpClint(): OkHttpClient {
        val inspector = HttpLoggingInterceptor()
        inspector.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().addInterceptor(inspector)
            .build()

    }


    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideBaseUrl(): String {
        return CloudConfig.BASE_URL
    }

    @Provides
    fun provideRetrofit(baseUrl: String, gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


}