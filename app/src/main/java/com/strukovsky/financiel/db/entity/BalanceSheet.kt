package com.strukovsky.financiel.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Share::class, childColumns = ["share_id"], parentColumns = ["_id"])])
data class BalanceSheet
    (
    /**
    Id of balance sheet object
     */
    @PrimaryKey(autoGenerate = true)
    val _id: Int,

    @ColumnInfo(index = true, name = "share_id")
    val shareId: Int,
    /*
    Here assets start. Assets are logically divided into current and long-term assets.
     */
    /**
    Current assets start with cash and cash equivalent. It's money on company's account.
     */
    val cash: Long,
    /**
    Marketable securities are shares,bonds, ETFs, etc company has aboard.
     */
    val securities: Long,

    /**
     * Accounts receivable is all money loaned from this company
     */
    @ColumnInfo(name = "accounts_receivable")
    val accountsReceivable: Long,

    /**
    Long-term assets section starts with Long-term investments
     */
    val investments: Long,

    /**
     * Fixed assets are all assets attributed to buildings, factories, inventories, utilities, etc of company
     */
    val fixed: Long,

    /**
     * Intangible assets are, for instance, intellectual property, goodwill.
     */

    /*
    Liabilities are logically divided into current and long-term liabilities
     */

    /**
     * Current liabilities start with accounts payable which represents money loaned from outside
     */
    val accountsPayable: Long,

    /**
     * Loans and notes payable
     */

    val notes: Long,

    /**
     * Accrued income taxes
     */

    val taxes: Long,

    /**
    Long-term liabilities are represented only by long-term debts
     */
    @ColumnInfo(name="long_term_debt")
    val longTermDebt: Long,

    /*
     *  Equity
     */

    /**
     * Surplus
     */
    val surplus: Long,

    /**
     * Reinvested earnings
     */
    @ColumnInfo(name = "reinvested_earnings")
    val reinvestedEarnings: Long,

    /**
     * Shareowners' equity
     */

    val shareowners: Long





)