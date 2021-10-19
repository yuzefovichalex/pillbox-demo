package com.alexyuzefovich.pillbox.ui.model.mapping

import com.alexyuzefovich.pillbox.ui.model.MedicineState
import com.alexyuzefovich.pillbox.ui.model.Pill

fun MedicineState.toPill(): Pill = Pill(
    id,
    name,
    notes,
    quantity, quantityMetric,
    dosage, dosageMetric,
    bestBeforeDate,
    type
)