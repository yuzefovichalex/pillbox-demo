package com.alexyuzefovich.pillbox.domain.repository

import com.alexyuzefovich.pillbox.data.source.MedicineDataSource
import com.alexyuzefovich.pillbox.domain.mapping.toPillData
import com.alexyuzefovich.pillbox.domain.mapping.toPills
import com.alexyuzefovich.pillbox.ui.model.Medicine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MedicineRepositoryImpl(
    private val medicineDataSource: MedicineDataSource
) : MedicineRepository {

    override fun getAllMedicines(): Flow<List<Medicine>> = medicineDataSource.getAllMedicines().map { it.toPills() }

    override suspend fun saveMedicine(medicine: Medicine) {
        medicineDataSource.saveMedicine(medicine.toPillData())
    }

}