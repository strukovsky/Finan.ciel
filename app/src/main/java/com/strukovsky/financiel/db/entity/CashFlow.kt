package com.strukovsky.financiel.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity
    (
    foreignKeys = [ForeignKey(entity = Share::class, childColumns = ["share_id"], parentColumns = ["id"])]
            )
data class CashFlow(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    @ColumnInfo(name="share_id")
    val shareId: Int,

    val revenue: Long,
    val costPrice: Long,
    val sales: Long,
    @ColumnInfo(name = "ebitda")
    val EBITDA: Long,
    @ColumnInfo(name = "free_cash_flow")
    val freeCashFlow: Long,
    @ColumnInfo(name = "net_income")
    val netIncome: Long
)