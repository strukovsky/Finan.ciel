package com.strukovsky.financiel.calculations

import com.strukovsky.financiel.db.entity.BalanceSheet
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class LiquidityTest {

    @Test
    public fun test()
    {
        val balance = BalanceSheet(
            1, 1,
            110000, 10000, 10000,
            10000, 100000, 100000,
            100000, 5000, 100000, 100000,
            10000, 100000, 10000, 10000
        )
        Assert.assertEquals(BigDecimal("0.00"), Liquidity.quickRatio(balance))
        Assert.assertEquals(BigDecimal("0.10"), Liquidity.currentRatio(balance))
    }

    @Test
    public fun testForZero()
    {
        val balance = BalanceSheet(
            1, 1,
            110000, 10000, 10000,
            10000, 100000, 100000,
            100000, 5000, 0, 100000,
            10000, 100000, 10000, 10000
        )
        Assert.assertEquals(null, Liquidity.quickRatio(balance))
        Assert.assertEquals(null, Liquidity.currentRatio(balance))
    }

    @Test
    public fun testForNegative()
    {
        val balance = BalanceSheet(
            1, 1,
            110000, 100000, 1000000,
            10000, 100000, 100000,
            100000, 5000, 10000, 100000,
            10000, 100000, 10000, 10000
        )
        Assert.assertEquals(null, Liquidity.quickRatio(balance))
    }
}