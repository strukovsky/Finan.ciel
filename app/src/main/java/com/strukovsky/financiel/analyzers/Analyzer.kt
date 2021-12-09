package com.strukovsky.financiel.analyzers

import com.strukovsky.financiel.calculations.CreditRatio
import com.strukovsky.financiel.calculations.Efficiency
import com.strukovsky.financiel.calculations.Liquidity
import com.strukovsky.financiel.calculations.Returns
import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.entity.CashFlow
import java.math.BigDecimal

object Analyzer {

    public fun analyzeNetIncomeToRevenue(cashFlow: CashFlow): Analysis {
        val value = Efficiency.netIncomeToRevenue(cashFlow)!!
        val recommendation = buildRecommendation(
            value,
            bottomValueForNeutral = BigDecimal("0.2"),
            bottomValueForPositive = BigDecimal("0.3")
        )
        val message = when (recommendation) {
            Recommendations.Positive -> "This param is good enough"
            Recommendations.Neutral -> "This param has normal value"
            else -> "This param has dangerous value"
        }
        val fieldName = "netIncomeToRevenue"
        return Analysis(recommendation, message, value.toString(), fieldName)
    }

    /**
     * Анализ значения с устанавливаемыми нижними порогами для хорошего и нейтрального статуса
     * Анализ проводится следующим образом:
     * плохо < bottomValueForNeutral <= нейтрально < bottomValueForPositive <= хорошо
     * Такой анализ подходит не для всех коэффициентов!
     * @return рекомендацию по этому значению: плохое оно, хорошее, нейтральное
     * @param value значение, по которому нужно сделать вывод
     * @param bottomValueForPositive нижнее значение, которое будет считаться достаточным для того,
     * чтобы считать value хорошим значением
     * @param bottomValueForNeutral нижнее значение, которое будет считаться достаточным для того,
     * чтобы считать value нейтральным
     * @param fieldName имя анализируемого поля (параметра)
     * Все значение, которые находятся ниже bottomValueForNeutral  будут считаться плохими значениями коэффициента
     */
    private fun buildRecommendation(
        value: BigDecimal,
        bottomValueForPositive: BigDecimal,
        bottomValueForNeutral: BigDecimal
    ) = if (value >= bottomValueForPositive)
        Recommendations.Positive
    else if (value < bottomValueForPositive && (value >= bottomValueForNeutral))
        Recommendations.Neutral
    else Recommendations.Negative

    fun analyzeReturnOnAssets(balanceSheet: BalanceSheet, cashFlow: CashFlow): Analysis {
        val value = Returns.returnOnAssets(balanceSheet, cashFlow)
        val recommendation = if (value == null)
            Recommendations.Neutral
        else buildRecommendation(
            value,
            bottomValueForNeutral = BigDecimal("0.05"),
            bottomValueForPositive = BigDecimal("0.1")
        )
        val message = when (recommendation) {
            Recommendations.Positive -> "ROA has good value"
            Recommendations.Neutral -> "ROA has average value"
            else -> "ROA is too little"
        }
        val fieldName = "Return on Assets (ROA)"
        val prettyValue = value?.toString() ?: "-"
        return Analysis(recommendation, message, prettyValue, fieldName)
    }

    fun analyzeReturnOnEquity(balanceSheet: BalanceSheet, cashFlow: CashFlow): Analysis {
        val value = Returns.returnOnEquity(balanceSheet, cashFlow)
        val recommendation = if (value == null)
            Recommendations.Neutral
        else buildRecommendation(
            value,
            bottomValueForNeutral = BigDecimal("0.03"),
            bottomValueForPositive = BigDecimal("0.08")
        )
        val message = when (recommendation) {
            Recommendations.Positive -> "ROE has brilliant value"
            Recommendations.Neutral -> "ROE has neutral value"
            else -> "ROE is dangerous"
        }
        val fieldName = "Return on Equity (ROE)"
        val prettyValue = value?.toString() ?: "-"
        return Analysis(recommendation, message, prettyValue, fieldName)

    }

    fun analyzeDebtToEquity(balanceSheet: BalanceSheet): Analysis {
        val value = CreditRatio.findDebtRatio(balanceSheet)
        val recommendation: Recommendations
        val message: String
        if (value > BigDecimal("0.67")) {
            recommendation = Recommendations.Negative
            message = "D/E is too big"
        } else if (value > BigDecimal("0.60")) {
            recommendation = Recommendations.Neutral
            message = "D/E is average"
        } else {
            recommendation = Recommendations.Positive
            message = "D/E has good value"
        }
        val fieldName = "Debt to Equity"
        return Analysis(recommendation, message, value.toString(), fieldName)
    }

    fun analyzeQuickRatio(balanceSheet: BalanceSheet): Analysis {
        val value = Liquidity.quickRatio(balanceSheet)
        val recommendation: Recommendations
        val message: String
        if (value == null) {
            recommendation = Recommendations.Neutral
            message = "Quick Ratio is unavailable"
        } else {
            if (value < BigDecimal.ONE) {
                recommendation = Recommendations.Positive
                message = "Quick Ratio is well enough"
            }
            else {
                recommendation = Recommendations.Negative
                message = "Quick Ratio is slightly big"
            }
        }
        val fieldName = "Quick Ratio"
        val prettyValue = value?.toString() ?: "-"
        return Analysis(recommendation, message, prettyValue, fieldName)
    }

    fun analyzeCurrentRatio(balanceSheet: BalanceSheet): Analysis {
        val value = Liquidity.currentRatio(balanceSheet)
        val recommendation: Recommendations
        val message: String
        if (value == null) {
            recommendation = Recommendations.Neutral
            message = "Current Ratio is unknown"
        } else {
            if (value < BigDecimal("1.5")) {
                recommendation = Recommendations.Positive
                message = "Current Ratio has good value"
            }
            else {
                recommendation = Recommendations.Negative
                message = "Current Ratio is slightly big"
            }
        }
        val fieldName = "Current Ratio"
        val prettyValue = value?.toString() ?: "-"
        return Analysis(recommendation, message, prettyValue, fieldName)
    }

}