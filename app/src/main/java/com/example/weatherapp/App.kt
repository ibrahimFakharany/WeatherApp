package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.base.di.ApplicationComponent
import com.example.weatherapp.base.di.CloudModule
import com.example.weatherapp.base.di.DaggerApplicationComponent
import com.example.weatherapp.base.di.DatabaseModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        applicationComponent = createApplicationComponent()
    }

    private fun createApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder().cloudModule(CloudModule()).databaseModule(
            DatabaseModule()
        ).build()
    }

    companion object {

        private var instance: App? = null
        lateinit var applicationComponent: ApplicationComponent


        fun get(): App {
            if (instance == null)
                throw IllegalStateException("no application attached!")
            return instance as App
        }

    }

}