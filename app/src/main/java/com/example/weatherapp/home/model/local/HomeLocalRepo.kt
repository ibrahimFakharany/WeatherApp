package com.example.weatherapp.home.model.local

import androidx.lifecycle.LiveData
import com.example.weatherapp.base.repo.local.dto.HistoryImages

interface HomeLocalRepo {

    fun saveImg(imgName: String?, thumbnail: ByteArray)
    fun getAllImages(): LiveData<List<HistoryImages>>
}