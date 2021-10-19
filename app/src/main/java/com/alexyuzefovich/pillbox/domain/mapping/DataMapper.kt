package com.alexyuzefovich.pillbox.domain.mapping

import com.alexyuzefovich.pillbox.data.db.model.MedicineData
import com.alexyuzefovich.pillbox.ui.model.DosageMetric
import com.alexyuzefovich.pillbox.ui.model.Medicine
import com.alexyuzefovich.pillbox.ui.model.QuantityMetric
import com.alexyuzefovich.pillbox.ui.model.Type

fun List<MedicineData>.toPills(): List<Medicine> = map { it.toPill() }

fun MedicineData.toPill(): Medicine = Medicine(
    id,
    name,
    notes,
    quantity, QuantityMetric.getBySimpleName(quantityMetric),
    dosage, DosageMetric.getBySimpleName(dosageMetric),
    bestBeforeDate,
    Type.getBySimpleName(type)
)


fun Medicine.toPillData(): MedicineData = MedicineData(
    id,
    name,
    notes,
    quantity, quantityMetric.simpleName,
    dosage, dosageMetric.simpleName,
    bestBeforeDate,
    type.toString()
)