package analyzers

import com.strukovsky.financiel.analyzers.Analysis
import com.strukovsky.financiel.analyzers.Analyzer
import com.strukovsky.financiel.analyzers.Recommendations
import com.strukovsky.financiel.calculations.Liquidity
import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.entity.CashFlow
import org.junit.Assert
import org.junit.Test

class AnalyzerTest {
    val balance = BalanceSheet(
        1, 1,
        110000, 100000, 1000000,
        10000, 100000, 100000,
        100000, 5000, 10000, 100000,
        10000, 100000, 10000, 10000
    )
    val cashFlow =  CashFlow(1, 1, 10000, 7000, 20000, 40000)
    @Test
    public fun testNetIncomeToRevenue()
    {
        val analysis = Analysis(
            Recommendations.Positive,
            "This param is good enough",
            "0.70",
            "netIncomeToRevenue"
        )
        Assert.assertEquals(analysis, Analyzer.analyzeNetIncomeToRevenue(cashFlow))
    }
    @Test
    public fun testReturnOnAssets()
    {
        val analysis = Analysis(
            Recommendations.Neutral,
            "ROA has average value",
            "0.06",
            "Return on Assets (ROA)"
        )
        Assert.assertEquals(analysis, Analyzer.analyzeReturnOnAssets(balance, cashFlow))
    }

    @Test
    public fun testReturnOnEquity()
    {
        val analysis = Analysis(
            Recommendations.Positive,
            "ROE has brilliant value",
            "0.70",
            "Return on Equity (ROE)"
        )
        Assert.assertEquals(analysis, Analyzer.analyzeReturnOnEquity(balance, cashFlow))
    }

    @Test
    public fun testDebtToEquity()
    {
        val analysis = Analysis(
            Recommendations.Positive,
            "D/E has good value",
            "0.50",
            "Debt to Equity"
        )
        Assert.assertEquals(analysis, Analyzer.analyzeDebtToEquity(balance))
    }

    @Test
    public fun testQuickRatio()
    {
        val analysis = Analysis(
            Recommendations.Neutral,
            "Quick Ratio is unavailable",
            "-",
            "Quick Ratio"
        )
        Assert.assertEquals(analysis, Analyzer.analyzeQuickRatio(balance))
    }

    @Test
    public fun testCurrentRatio()
    {
        val analysis = Analysis(
            Recommendations.Negative,
            "Current Ratio is slightly big",
            "10.00",
            "Current Ratio"
        )
        Assert.assertEquals(analysis, Analyzer.analyzeCurrentRatio(balance))
    }

}