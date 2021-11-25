package com.strukovsky.financiel.calculations

import com.strukovsky.financiel.calculations.Returns
import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.entity.CashFlow
import org.junit.Assert
import org.junit.Test

class ReturnsTest {
    @Test
    fun testROE()
    {
        val balanceSheet = BalanceSheet(1, 1,
            110000, 10000,10000,
            10000,100000,100000,
            100000,10000,100000,100000,
            10000,100000, 10000,10000)
        val cashFlow = CashFlow(1,1,10000,20000,10000, 10000)
        val actual = Returns.returnOnEquity(balanceSheet, cashFlow)
        Assert.assertEquals("2.00", actual)
    }

    @Test
    fun testROA()
    {
        val balanceSheet = BalanceSheet(1, 1,
            110000, 10000,10000,
            10000,100000,100000,
            100000,10000,100000,100000,
            10000,100000, 10000,10000)
        val cashFlow = CashFlow(1,1,10000,20000,10000, 10000)
        val actual = Returns.returnOnAssets(balanceSheet, cashFlow)
        Assert.assertEquals("0.18", actual)
    }

    @Test
    fun testNegativeROE()
    {
        val balanceSheet = BalanceSheet(1, 1,
            110000, 10000,10000,
            10000,100000,100000,
            100000,10000,100000,100000,
            10000,100000, 10000,10000)
        val cashFlow = CashFlow(1,1,10000,-2000,10000, 10000)
        val actual = Returns.returnOnEquity(balanceSheet, cashFlow)
        Assert.assertEquals("-0.20", actual)
    }

    @Test
    fun testZeroROE()
    {
        val balanceSheet = BalanceSheet(1, 1,
            110000, 10000,10000,
            10000,100000,100000,
            100000,110000,100000,100000,
            10000,100000, 10000,0)
        val cashFlow = CashFlow(1,1,10000,-20000,10000, 10000)
        val actual = Returns.returnOnEquity(balanceSheet, cashFlow)
        Assert.assertEquals(null, actual)
    }

    @Test
    fun testZeroROA()
    {
        val balanceSheet = BalanceSheet(1, 1,
            0, 10000,10000,
            10000,100000,100000,
            100000,110000,100000,100000,
            10000,100000, 10000,10000)
        val cashFlow = CashFlow(1,1,10000,-20000,10000, 10000)
        val actual = Returns.returnOnAssets(balanceSheet, cashFlow)
        Assert.assertEquals(null, actual)
    }
}