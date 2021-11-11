package com.strukovsky.financiel.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.strukovsky.financiel.db.entity.Share

@Dao
interface ShareDao
{
    @Query("SELECT * FROM share")
    fun getAllShares(): List<Share>

    @Insert
    fun addShare(share: Share)

    @Query("SELECT * FROM share WHERE ticker LIKE '%' || :query || '%'")
    fun findByTicker(query: String): List<Share>

    @Query("SELECT * FROM share WHERE name LIKE '%' || :query || '%'")
    fun findByName(query: String): List<Share>

    @Query("SELECT * FROM share WHERE name LIKE '%' || :query || '%' OR ticker LIKE '%' || :query || '%'")
    fun findByNameOrTicker(query: String): List<Share>

    @Query("SELECT * FROM share WHERE _id = :id")
    fun findById(id: Int): Share
}