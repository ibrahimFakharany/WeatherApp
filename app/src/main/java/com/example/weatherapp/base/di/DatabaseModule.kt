package com.example.weatherapp.base.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.example.weatherapp.App
import com.example.weatherapp.base.repo.local.dao.HistoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        val builder = Room.databaseBuilder(context, AppDatabase::class.java, "weather.db")
            .fallbackToDestructiveMigration()

        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideContext() = App.get() as Context


    /**
     * Dao
     */
    @Singleton
    @Provides
    fun provideUserActivityDao(db: AppDatabase): HistoryDao = db.historyDao()
}