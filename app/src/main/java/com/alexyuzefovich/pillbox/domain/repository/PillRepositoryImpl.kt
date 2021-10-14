package com.alexyuzefovich.pillbox.domain.repository

import com.alexyuzefovich.pillbox.data.source.PillDataSource
import com.alexyuzefovich.pillbox.domain.mapping.toPills
import com.alexyuzefovich.pillbox.ui.model.Pill
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PillRepositoryImpl(
    private val pillDataSource: PillDataSource
) : PillRepository {

    override fun getAllPills(): Flow<List<Pill>> = pillDataSource.getAllPills().map { it.toPills() }

}