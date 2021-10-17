package com.strukovsky.financiel.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Share(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    val ticker: String,
    val country: String,
    val industry: String,
    val description: String,
    @ColumnInfo(name = "shares_outstanding")
    val sharesOutstanding: Long
)