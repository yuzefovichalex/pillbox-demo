package com.alexyuzefovich.pillbox.data.source

import com.alexyuzefovich.pillbox.data.db.model.MedicineData
import kotlinx.coroutines.flow.Flow

interface MedicineDataSource {

    fun getAllPills(): Flow<List<MedicineData>>
    suspend fun savePill(medicineData: MedicineData)

}