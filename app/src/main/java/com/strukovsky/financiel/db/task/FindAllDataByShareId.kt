package com.strukovsky.financiel.db.task

import android.content.Context
import android.os.Bundle

class FindAllDataByShareId(context: Context, private val id: Int): AbstractTask<ShareDataBundle>(context) {
    override fun call(): ShareDataBundle {
        val share = db!!.shareDao.findById(id)
        val cashFlow = db.cashFlowDao.findByShareId(id)
        val balanceSheet = db.balanceSheetDao.findByShareId(id)
        return ShareDataBundle(share, cashFlow, balanceSheet)
    }
}