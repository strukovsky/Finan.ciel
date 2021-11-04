package com.strukovsky.financiel.calculations

import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.entity.CashFlow
import java.math.BigDecimal
import java.math.RoundingMode

object Returns {
    fun returnOnAssets(balanceSheet: BalanceSheet, cashFlow: CashFlow): String
    {
        val assets = BigDecimal(balanceSheet.assets)
        val netIncome = BigDecimal(cashFlow.netIncome)
        return netIncome.divide(assets, 2, RoundingMode.HALF_UP).toString()
    }

    fun returnOnEquity(balanceSheet: BalanceSheet, cashFlow: CashFlow): String
    {
        val equity = BigDecimal(balanceSheet.assets - balanceSheet.liabilities)
        val netIncome = BigDecimal(cashFlow.netIncome)
        return netIncome.divide(equity, 2, RoundingMode.HALF_UP).toString()
    }
}