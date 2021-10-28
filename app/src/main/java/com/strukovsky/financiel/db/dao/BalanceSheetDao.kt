package com.strukovsky.financiel.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.strukovsky.financiel.db.entity.BalanceSheet

@Dao
interface BalanceSheetDao {
    @Query("SELECT * FROM BalanceSheet")
    fun getAllBalanceSheet(): List<BalanceSheet>

}