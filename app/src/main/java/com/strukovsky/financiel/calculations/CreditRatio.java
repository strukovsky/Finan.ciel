package com.strukovsky.financiel.calculations;
import com.strukovsky.financiel.db.entity.BalanceSheet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditRatio {
    public static String findDebtRatio(BalanceSheet balanceSheet){

        BigDecimal debt = BigDecimal.valueOf(balanceSheet.getLiabilities());
        BigDecimal equity = BigDecimal.valueOf(balanceSheet.getEquity());
        BigDecimal r =  debt.divide(equity, 2, RoundingMode.HALF_UP);
       // BigDecimal res = r.setScale();
        return r.toString();
    }

}