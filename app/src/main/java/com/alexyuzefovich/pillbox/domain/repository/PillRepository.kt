package com.alexyuzefovich.pillbox.domain.repository

import com.alexyuzefovich.pillbox.ui.model.Pill
import kotlinx.coroutines.flow.Flow

interface PillRepository {

    fun getAllPills(): Flow<List<Pill>>

}