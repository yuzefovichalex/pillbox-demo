package com.alexyuzefovich.pillbox.domain.repository

import com.alexyuzefovich.pillbox.ui.model.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {

    fun getAllPills(): Flow<List<Medicine>>
    suspend fun savePill(medicine: Medicine)

}