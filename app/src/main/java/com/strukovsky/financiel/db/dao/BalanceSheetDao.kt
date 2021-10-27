package com.strukovsky.financiel.db.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BalanceSheetDao {
    @Query("SELECT * FROM BalanceSheet")
    fun getAllBalanceSheet()

}