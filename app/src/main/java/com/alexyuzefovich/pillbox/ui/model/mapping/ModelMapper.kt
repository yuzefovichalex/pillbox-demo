package com.alexyuzefovich.pillbox.ui.model.mapping

import com.alexyuzefovich.pillbox.ui.model.MedicineState
import com.alexyuzefovich.pillbox.ui.model.Medicine

fun MedicineState.toPill(): Medicine = Medicine(
    id,
    name,
    notes,
    quantity, quantityMetric,
    dosage.takeIf { it > 0 }, dosageMetric,
    bestBeforeDate,
    type
)