package com.alexyuzefovich.pillbox.data.source

import com.alexyuzefovich.pillbox.data.db.dao.MedicineDao
import com.alexyuzefovich.pillbox.data.db.model.MedicineData
import kotlinx.coroutines.flow.Flow

class MedicineDataSourceImpl(
    private val medicineDao: MedicineDao
) : MedicineDataSource {

    override fun getAllPills(): Flow<List<MedicineData>> = medicineDao.getAllPills()

    override suspend fun savePill(medicineData: MedicineData) {
        medicineDao.savePill(medicineData)
    }

}