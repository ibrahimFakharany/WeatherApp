package com.example.weatherapp.base.repo.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherapp.base.repo.local.dto.HistoryImages


@Dao
abstract class HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertImages(image: HistoryImages)

    @Transaction
    @Query("SELECT * FROM history")
    abstract fun getAllImages(): LiveData<List<HistoryImages>>


}