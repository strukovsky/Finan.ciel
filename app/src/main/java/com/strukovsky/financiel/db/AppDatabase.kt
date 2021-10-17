package com.strukovsky.financiel.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.strukovsky.financiel.db.dao.ShareDao
import com.strukovsky.financiel.db.entity.CashFlow
import com.strukovsky.financiel.db.entity.Share

@Database(entities = [Share::class], version = 1)
public abstract class AppDatabase: RoomDatabase() {
   public abstract val shareDao: ShareDao
}