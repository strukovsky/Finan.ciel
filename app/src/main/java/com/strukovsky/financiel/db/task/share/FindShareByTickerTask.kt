package com.strukovsky.financiel.db.task.share

import android.content.Context
import com.strukovsky.financiel.db.MainDatabase
import com.strukovsky.financiel.db.entity.Share
import com.strukovsky.financiel.db.task.AbstractTask
import java.util.concurrent.Callable

class FindShareByTickerTask(context: Context, private val query: String): AbstractTask<List<Share>>(context) {
    override fun call(): List<Share>? {
        return db?.shareDao?.findByTicker(query)

    }
}