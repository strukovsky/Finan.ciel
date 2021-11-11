package com.strukovsky.financiel.db.task.share

import android.content.Context
import com.strukovsky.financiel.db.entity.Share
import com.strukovsky.financiel.db.task.AbstractTask

class FindShareByNameTask(context: Context, val query:String): AbstractTask<List<Share>>(context) {
    override fun call(): List<Share>? {
        return db?.shareDao?.findByName(query)
    }
}