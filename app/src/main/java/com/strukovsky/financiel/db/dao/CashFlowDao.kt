package com.strukovsky.financiel.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.strukovsky.financiel.db.entity.CashFlow

@Dao
interface CashFlowDao {
    @Query("SELECT * FROM CashFlow")
    fun getAllCashFlow(): List<CashFlow>
    @Insert
    fun addCashFlow(cashFlow: CashFlow)

    @Query("SELECT * FROM CashFlow WHERE share_id = :id")
    fun findByShareId(id: Int):CashFlow

}