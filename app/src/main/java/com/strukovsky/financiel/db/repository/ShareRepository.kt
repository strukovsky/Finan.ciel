package com.strukovsky.financiel.db.repository

import com.strukovsky.financiel.db.AppDatabase
import com.strukovsky.financiel.db.dao.ShareDao
import com.strukovsky.financiel.db.entity.Share

class ShareRepository(private val database: AppDatabase) {
    private val shareDao: ShareDao = database.shareDao
    fun getAllShares() = shareDao.getAllShares()
    fun addShare(share: Share)
    {
        shareDao.addShare(share)
    }
}