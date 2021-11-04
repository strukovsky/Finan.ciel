package com.strukovsky.financiel.db.task

import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.entity.CashFlow
import com.strukovsky.financiel.db.entity.Share

data class ShareDataBundle(val share: Share, val cashFlow: CashFlow, val balanceSheet: BalanceSheet)