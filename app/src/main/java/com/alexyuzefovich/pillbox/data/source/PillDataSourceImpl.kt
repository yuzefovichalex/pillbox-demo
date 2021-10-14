package com.alexyuzefovich.pillbox.data.source

import com.alexyuzefovich.pillbox.data.db.dao.PillDao
import com.alexyuzefovich.pillbox.data.db.model.PillData
import kotlinx.coroutines.flow.Flow

class PillDataSourceImpl(
    private val pillDao: PillDao
) : PillDataSource {

    override fun getAllPills(): Flow<List<PillData>> = pillDao.getAllPills()

}