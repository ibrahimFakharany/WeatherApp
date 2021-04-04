package com.example.weatherapp.base.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.base.repo.local.dao.HistoryDao
import com.example.weatherapp.base.repo.local.dto.HistoryImages


@Database(entities = [HistoryImages::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}