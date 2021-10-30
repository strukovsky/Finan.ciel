package com.strukovsky.financiel.db.task.balance_sheet

import android.content.Context
import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.task.AbstractTask

class AddBalanceSheetTask(context: Context, private val balanceSheet: BalanceSheet): AbstractTask<Boolean>(context) {
    override fun call(): Boolean {
        db?.balanceSheetDao?.addBalanceSheet(balanceSheet)
        return true
    }
}