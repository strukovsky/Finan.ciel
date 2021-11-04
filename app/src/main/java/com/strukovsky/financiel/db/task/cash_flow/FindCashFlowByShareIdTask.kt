package com.strukovsky.financiel.db.task.cash_flow

import android.content.Context
import com.strukovsky.financiel.db.entity.CashFlow
import com.strukovsky.financiel.db.task.AbstractTask

class FindCashFlowByShareIdTask(context: Context, private val id: Int): AbstractTask<CashFlow>(context) {
    override fun call() = db?.cashFlowDao?.findByShareId(id)
}