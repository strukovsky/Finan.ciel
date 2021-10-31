package com.strukovsky.financiel.db.task.share

import android.content.Context
import com.strukovsky.financiel.db.entity.Share
import com.strukovsky.financiel.db.task.AbstractTask
import java.util.concurrent.Callable

class AddAllSharesTask(context: Context, private val shares: List<Share>): AbstractTask<Boolean>(context) {
    override fun call(): Boolean {
        shares.forEach { db?.shareDao?.addShare(it) }
        return true;
    }
}