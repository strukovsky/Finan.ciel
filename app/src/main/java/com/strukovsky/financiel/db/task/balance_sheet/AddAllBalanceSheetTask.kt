package com.strukovsky.financiel.db.task.balance_sheet

import android.content.Context
import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.task.AbstractTask

class AddAllBalanceSheetTask(context: Context, private val balanceSheets: List<BalanceSheet>): AbstractTask<Boolean>(context) {
    override fun call(): Boolean {
        balanceSheets.forEach { db?.balanceSheetDao?.addBalanceSheet(it) }
        return true
    }
}