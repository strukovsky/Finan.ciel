package com.strukovsky.financiel.db.task.cash_flow

import android.content.Context
import com.strukovsky.financiel.db.entity.CashFlow
import com.strukovsky.financiel.db.task.AbstractTask

class ReadAllCashFlowTask(context: Context): AbstractTask<List<CashFlow>>(context) {
    override fun call(): List<CashFlow>? {
        return db?.cashFlowDao?.getAllCashFlow()
    }
}