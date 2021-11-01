package com.alexyuzefovich.pillbox

import android.app.Application
import com.alexyuzefovich.pillbox.data.db.PillBoxDatabase
import com.alexyuzefovich.pillbox.data.source.MedicineDataSource
import com.alexyuzefovich.pillbox.data.source.MedicineDataSourceImpl
import com.alexyuzefovich.pillbox.domain.repository.MedicineRepository
import com.alexyuzefovich.pillbox.domain.repository.MedicineRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PillBoxApplication : Application() {

    private val pillBoxDatabase: PillBoxDatabase by lazy { PillBoxDatabase.getDatabase(this, CoroutineScope(SupervisorJob())) }
    private val medicineDataSource: MedicineDataSource by lazy { MedicineDataSourceImpl(pillBoxDatabase.medicineDao()) }
    val medicineRepository: MedicineRepository by lazy { MedicineRepositoryImpl(medicineDataSource) }

}