package com.alexyuzefovich.pillbox.data.source

import com.alexyuzefovich.pillbox.data.db.model.PillData
import kotlinx.coroutines.flow.Flow

interface PillDataSource {

    fun getAllPills(): Flow<List<PillData>>
    suspend fun savePill(pillData: PillData)

}