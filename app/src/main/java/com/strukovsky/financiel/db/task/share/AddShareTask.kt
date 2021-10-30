package com.strukovsky.financiel.db.task.share

import android.content.Context
import com.strukovsky.financiel.db.entity.Share
import com.strukovsky.financiel.db.task.AbstractTask

class AddShareTask(context: Context, private val share: Share): AbstractTask<Boolean>(context) {
    override fun call(): Boolean {
        db?.shareDao?.addShare(share)
        return true
    }
}