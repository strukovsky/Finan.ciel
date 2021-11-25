package com.strukovsky.financiel.calculations;
import com.strukovsky.financiel.db.entity.BalanceSheet;

import java.math.BigDecimal;

public class CreditRatio {
    public static String findDebtRatio(BalanceSheet balanceSheet){

        BigDecimal debt = BigDecimal.valueOf(balanceSheet.getLiabilities());
        BigDecimal equity = BigDecimal.valueOf(balanceSheet.getEquity());
        BigDecimal r =  debt.divide(equity);
        BigDecimal res = r.setScale(2, BigDecimal.ROUND_HALF_UP);
        return res.toString();
    }

}