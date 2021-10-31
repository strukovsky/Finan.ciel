package com.strukovsky.financiel.db.task.share

import android.content.Context
import com.strukovsky.financiel.db.entity.Share
import com.strukovsky.financiel.db.task.AbstractTask

class FindShareByIdTask(context: Context, private val id: Int): AbstractTask<Share>(context) {
    override fun call(): Share? {
        return db?.shareDao?.findById(id)
    }

}