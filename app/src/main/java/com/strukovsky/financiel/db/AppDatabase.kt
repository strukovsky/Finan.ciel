package com.strukovsky.financiel.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.strukovsky.financiel.db.dao.BalanceSheetDao
import com.strukovsky.financiel.db.dao.CashFlowDao
import com.strukovsky.financiel.db.dao.ShareDao
import com.strukovsky.financiel.db.entity.BalanceSheet
import com.strukovsky.financiel.db.entity.CashFlow
import com.strukovsky.financiel.db.entity.Share

@Database(entities = [Share::class, CashFlow::class, BalanceSheet::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
   abstract val shareDao: ShareDao
   abstract val cashFlowDao: CashFlowDao
   abstract val balanceSheetDao: BalanceSheetDao


}