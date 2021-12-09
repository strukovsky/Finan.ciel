package com.strukovsky.financiel.calculations

import com.strukovsky.financiel.db.entity.CashFlow
import java.math.BigDecimal
import java.math.RoundingMode

object Efficiency {
    fun netIncomeToRevenue(cashFlow: CashFlow): BigDecimal?{
        val netIncome = BigDecimal(cashFlow.netIncome)
        val revenue = BigDecimal(cashFlow.revenue)
        if(revenue <= BigDecimal.ZERO || netIncome <= BigDecimal.ZERO)
            return null
        val result = netIncome.divide(revenue, 2, RoundingMode.HALF_UP)
        return result
    }
}