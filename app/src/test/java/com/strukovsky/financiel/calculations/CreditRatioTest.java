package com.strukovsky.financiel.calculations;

import com.strukovsky.financiel.db.entity.BalanceSheet;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class CreditRatioTest {
    @Test
    public void test(){
        BalanceSheet q = new BalanceSheet(1, 1,
                110000, 10000,10000,
                10000,100000,100000,
                100000,5000,100000,100000,
                10000,100000, 10000,10000);
        Assert.assertEquals(new BigDecimal("0.50"), CreditRatio.findDebtRatio(q));
    }
}
