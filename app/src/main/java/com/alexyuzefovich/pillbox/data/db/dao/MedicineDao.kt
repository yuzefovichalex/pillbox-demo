package com.alexyuzefovich.pillbox.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexyuzefovich.pillbox.data.db.model.MedicineData
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Query("SELECT * FROM medicine_table ORDER BY name ASC")
    fun getAllMedicines(): Flow<List<MedicineData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMedicine(medicineData: MedicineData)

    @Query("DELETE FROM medicine_table")
    suspend fun deleteAll()

}