package com.strukovsky.financiel.db.task.cash_flow

import android.content.Context
import com.strukovsky.financiel.db.entity.CashFlow
import com.strukovsky.financiel.db.task.AbstractTask

class AddAllCashFlowTask(context: Context, private val cashFlows: List<CashFlow>): AbstractTask<Boolean>(context) {
    override fun call(): Boolean {
        cashFlows.forEach { db?.cashFlowDao?.addCashFlow(it) }
        return true
    }
}