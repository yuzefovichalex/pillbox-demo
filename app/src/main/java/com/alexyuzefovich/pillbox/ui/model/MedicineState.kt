package com.alexyuzefovich.pillbox.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MedicineState private constructor() {

    companion object {
        val EMPTY: MedicineState
            get() = fromPill(Pill.EMPTY)

        fun fromPill(pill: Pill): MedicineState = MedicineState().apply {
            id = pill.id
            name = pill.name
            notes = pill.notes.orEmpty()
            quantity = pill.quantity
            quantityMetric = pill.quantityMetric
            dosage = pill.dosage ?: 0
            dosageMetric = pill.dosageMetric
            bestBeforeDate = pill.bestBeforeDate
            type = pill.type
        }
    }

    var id: Long = Pill.NO_ID
    var name: String by mutableStateOf("")
    var notes: String by mutableStateOf("")
    var quantity: Int by mutableStateOf(0)
    var quantityMetric: QuantityMetric by mutableStateOf(QuantityMetric.PIECES)
    var dosage: Int by mutableStateOf(0)
    var dosageMetric: DosageMetric by mutableStateOf(DosageMetric.MILLIGRAMS)
    var bestBeforeDate: Long by mutableStateOf(0L)
    var type: Type by mutableStateOf(Type.TABLET)

}