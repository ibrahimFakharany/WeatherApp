package com.example.weatherapp.home.model.local

import androidx.lifecycle.LiveData
import com.example.weatherapp.base.repo.local.dao.HistoryDao
import com.example.weatherapp.base.repo.local.dto.HistoryImages
import javax.inject.Inject

class HomeLocalRepoImpl @Inject constructor(val historyDao: HistoryDao) : HomeLocalRepo {
    override fun saveImg(imgName: String?, thumbnail: ByteArray) {
        var historyImage = HistoryImages()
        historyImage.data = imgName
        historyImage.thumbail = thumbnail
        historyDao.insertImages(historyImage)
    }

    override fun getAllImages(): LiveData<List<HistoryImages>> {
        return historyDao.getAllImages()
    }

}