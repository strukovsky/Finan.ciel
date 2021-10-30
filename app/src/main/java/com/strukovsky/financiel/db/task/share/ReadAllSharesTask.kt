package com.strukovsky.financiel.db.task.share

import android.content.Context
import com.strukovsky.financiel.db.MainDatabase
import com.strukovsky.financiel.db.entity.Share
import java.util.concurrent.Callable

class ReadAllSharesTask(private val context: Context): Callable<List<Share>>{

    override fun call(): List<Share>? {
        val db = MainDatabase.getInstance(context)
        return db?.shareDao?.getAllShares()
    }

}