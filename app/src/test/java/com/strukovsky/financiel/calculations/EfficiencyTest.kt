package com.strukovsky.financiel.calculations

import com.strukovsky.financiel.calculations.Efficiency
import com.strukovsky.financiel.db.entity.CashFlow
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class EfficiencyTest {
    @Test
    fun test()
    {
        val q = CashFlow(1, 1, 10000, 7000, 20000, 40000)
        val actual = Efficiency.netIncomeToRevenue(q)
        Assert.assertEquals("0.70", actual)
    }

    @Test
    fun testNegative()
    {
        val q = CashFlow(1, 1, -10000, 0, 20000, 40000)
        val actual = Efficiency.netIncomeToRevenue(q)
        Assert.assertEquals(null, actual)
    }


}