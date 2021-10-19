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

    override fun getAllPills(): Flow<List<Medicine>> = medicineDataSource.getAllPills().map { it.toPills() }

    override suspend fun savePill(medicine: Medicine) {
        medicineDataSource.savePill(medicine.toPillData())
    }

}