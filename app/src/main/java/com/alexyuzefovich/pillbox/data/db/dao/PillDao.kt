package com.alexyuzefovich.pillbox.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexyuzefovich.pillbox.data.db.model.PillData
import kotlinx.coroutines.flow.Flow

@Dao
interface PillDao {

    @Query("SELECT * FROM pill_table ORDER BY name ASC")
    fun getAllPills(): Flow<List<PillData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPill(pillData: PillData)

    @Query("DELETE FROM pill_table")
    suspend fun deleteAll()

}