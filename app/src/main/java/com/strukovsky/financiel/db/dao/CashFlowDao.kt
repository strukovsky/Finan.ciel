package com.strukovsky.financiel.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.strukovsky.financiel.db.entity.CashFlow

@Dao
interface CashFlowDao {
    @Query("SELECT * FROM CashFlow")
    fun getAllCashFlow(): List<CashFlow>
}