package com.strukovsky.financiel.calculations;
import com.strukovsky.financiel.db.entity.BalanceSheet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Liquidity {

    public static BigDecimal quickRatio(BalanceSheet balanceSheet){
        BigDecimal currentLiabilities = BigDecimal.valueOf(balanceSheet.getCurrentLiabilities());
        BigDecimal currentAssets = BigDecimal.valueOf(balanceSheet.getCurrentAssets());
        BigDecimal cash = BigDecimal.valueOf(balanceSheet.getCash());
        BigDecimal subtract = currentAssets.subtract(cash);
        if(subtract.compareTo(BigDecimal.ZERO) < 0)
            return null;
        if(Objects.equals(currentLiabilities, BigDecimal.ZERO))
            return null;
        return subtract.divide(currentLiabilities, 2, RoundingMode.HALF_UP);

    }
    public static BigDecimal currentRatio(BalanceSheet balanceSheet){
        BigDecimal currentLiabilities = BigDecimal.valueOf(balanceSheet.getCurrentLiabilities());
        BigDecimal currentAssets = BigDecimal.valueOf(balanceSheet.getCurrentAssets());
        if(Objects.equals(currentLiabilities, BigDecimal.ZERO))
            return null;
        return currentAssets.divide(currentLiabilities, 2, RoundingMode.HALF_UP);
    }
}
