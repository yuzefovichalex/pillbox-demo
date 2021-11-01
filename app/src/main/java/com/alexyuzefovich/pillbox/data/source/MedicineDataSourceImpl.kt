package com.alexyuzefovich.pillbox.data.source

import com.alexyuzefovich.pillbox.data.db.dao.MedicineDao
import com.alexyuzefovich.pillbox.data.db.model.MedicineData
import kotlinx.coroutines.flow.Flow

class MedicineDataSourceImpl(
    private val medicineDao: MedicineDao
) : MedicineDataSource {

    override fun getAllMedicines(): Flow<List<MedicineData>> = medicineDao.getAllMedicines()

    override suspend fun saveMedicine(medicineData: MedicineData) {
        medicineDao.saveMedicine(medicineData)
    }

}