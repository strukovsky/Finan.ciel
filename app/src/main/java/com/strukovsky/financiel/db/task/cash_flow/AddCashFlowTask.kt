package com.strukovsky.financiel.db.task.cash_flow

import android.content.Context
import com.strukovsky.financiel.db.entity.CashFlow
import com.strukovsky.financiel.db.task.AbstractTask
import java.util.concurrent.Callable

class AddCashFlowTask(context: Context, private val cashFlow: CashFlow): AbstractTask<Boolean>(context) {
    override fun call(): Boolean {
        db?.cashFlowDao?.addCashFlow(cashFlow)
        return true
    }
}