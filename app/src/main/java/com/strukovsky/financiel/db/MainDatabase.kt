package com.strukovsky.financiel.db

import android.content.Context
import androidx.room.Room

object MainDatabase {

    private var database: AppDatabase? = null

    public fun getInstance(context: Context): AppDatabase?
    {
        if (database == null)
        {
            synchronized(this)
            {
                database = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
            }
        }
        return database
    }
}