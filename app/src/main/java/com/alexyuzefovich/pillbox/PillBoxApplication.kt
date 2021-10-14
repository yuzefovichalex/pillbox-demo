package com.alexyuzefovich.pillbox

import android.app.Application
import com.alexyuzefovich.pillbox.data.db.PillBoxDatabase
import com.alexyuzefovich.pillbox.data.source.PillDataSource
import com.alexyuzefovich.pillbox.data.source.PillDataSourceImpl
import com.alexyuzefovich.pillbox.domain.repository.PillRepository
import com.alexyuzefovich.pillbox.domain.repository.PillRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PillBoxApplication : Application() {

    private val pillBoxDatabase: PillBoxDatabase by lazy { PillBoxDatabase.getDatabase(this, CoroutineScope(SupervisorJob())) }
    private val pillDataSource: PillDataSource by lazy { PillDataSourceImpl(pillBoxDatabase.pillDao()) }
    val pillRepository: PillRepository by lazy { PillRepositoryImpl(pillDataSource) }

}