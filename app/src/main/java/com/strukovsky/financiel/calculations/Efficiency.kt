package com.strukovsky.financiel.calculations

import com.strukovsky.financiel.db.entity.CashFlow
import java.math.BigDecimal
import java.math.RoundingMode

object Efficiency {
    fun netIncomeToRevenue(cashFlow: CashFlow): String{
        val netIncome = BigDecimal(cashFlow.netIncome)
        val revenue = BigDecimal(cashFlow.revenue)
        val result = netIncome.divide(revenue, 2, RoundingMode.HALF_UP)
        return result.toString()
    }
}