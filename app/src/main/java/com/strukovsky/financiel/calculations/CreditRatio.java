package com.strukovsky.financiel.calculations;
import com.strukovsky.financiel.db.entity.BalanceSheet;


import java.math.BigDecimal;

public class CreditRatio {
    public static double findDebtRatio(BalanceSheet d, BalanceSheet e){

        BigDecimal debt = BigDecimal.valueOf(d.getLiabilities());
        BigDecimal equity = BigDecimal.valueOf(e.getEquity());
        BigDecimal r =  debt.divide(equity);
        BigDecimal res = r.setScale(2, BigDecimal.ROUND_HALF_UP);
        double result = res.doubleValue();
        return result;
    }

}