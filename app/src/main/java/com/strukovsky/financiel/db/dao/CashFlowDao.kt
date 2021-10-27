package com.strukovsky.financiel.db.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CashFlowDao {
    @Query("SELECT * FROM CashFlow")
    fun getAllCashFlow()
}