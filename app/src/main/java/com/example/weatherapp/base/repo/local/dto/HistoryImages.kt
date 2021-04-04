package com.example.weatherapp.base.repo.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "history")
data class HistoryImages(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "image_name")
    var data: String? = null,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var thumbail: ByteArray? = null
)