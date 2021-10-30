package com.strukovsky.financiel.db.task.balance_sheet

import android.content.Context
import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.task.AbstractTask

class ReadAllBalanceSheetTask(context: Context): AbstractTask<List<BalanceSheet>>(context) {
    override fun call(): List<BalanceSheet>? {
        return db?.balanceSheetDao?.getAllBalanceSheet()
    }
}