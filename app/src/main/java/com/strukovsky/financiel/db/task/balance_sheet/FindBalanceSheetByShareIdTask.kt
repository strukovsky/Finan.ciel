package com.strukovsky.financiel.db.task.balance_sheet

import android.content.Context
import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.task.AbstractTask

class FindBalanceSheetByShareIdTask(context: Context, private val shareId: Int) : AbstractTask<BalanceSheet>(context) {
    override fun call() =
        db?.balanceSheetDao?.findByShareId(shareId);

}