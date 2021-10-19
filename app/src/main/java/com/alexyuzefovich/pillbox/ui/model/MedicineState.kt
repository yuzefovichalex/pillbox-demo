package com.alexyuzefovich.pillbox.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MedicineState private constructor() {

    companion object {
        val EMPTY: MedicineState
            get() = fromPill(Medicine.EMPTY)

        fun fromPill(medicine: Medicine): MedicineState = MedicineState().apply {
            id = medicine.id
            name = medicine.name
            notes = medicine.notes.orEmpty()
            quantity = medicine.quantity
            quantityMetric = medicine.quantityMetric
            dosage = medicine.dosage ?: 0
            dosageMetric = medicine.dosageMetric
            bestBeforeDate = medicine.bestBeforeDate
            type = medicine.type
        }
    }

    var id: Long = Medicine.NO_ID
    var name: String by mutableStateOf("")
    var notes: String by mutableStateOf("")
    var quantity: Int by mutableStateOf(0)
    var quantityMetric: QuantityMetric by mutableStateOf(QuantityMetric.PIECES)
    var dosage: Int by mutableStateOf(0)
    var dosageMetric: DosageMetric by mutableStateOf(DosageMetric.MILLIGRAMS)
    var bestBeforeDate: Long by mutableStateOf(0L)
    var type: Type by mutableStateOf(Type.TABLET)

}