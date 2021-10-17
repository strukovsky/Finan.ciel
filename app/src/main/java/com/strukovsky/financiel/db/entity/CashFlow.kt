package com.strukovsky.financiel.db.entity

import androidx.room.Entity
import java.util.*

data class CashFlow(
    val share: Share,
    val revenue: Long,
    val income: Long,
    val date: Date
)