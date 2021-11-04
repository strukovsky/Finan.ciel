package com.strukovsky.financiel.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity
    (
    foreignKeys = [ForeignKey(entity = Share::class, childColumns = ["share_id"], parentColumns = ["_id"])]
            )
data class CashFlow(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    @ColumnInfo(name="share_id", index = true)
    val shareId: Int,
    /**
     * Gross money earned by company by operating (primary) activity
     */
    val revenue: Long,
    /**
     * Net income gained by primary activity
     */
    @ColumnInfo(name = "net_income")
    val netIncome: Long,

    /**
     * Cash involved into non-operating activity i.e. investing
     */
    val investing: Long,

    /**
     * Cash involved into non-operating activity i.e. finance
     */
    val financing: Long






)