package com.alexyuzefovich.pillbox.domain.mapping

import com.alexyuzefovich.pillbox.data.db.model.PillData
import com.alexyuzefovich.pillbox.ui.model.DosageMetric
import com.alexyuzefovich.pillbox.ui.model.Pill
import com.alexyuzefovich.pillbox.ui.model.QuantityMetric
import com.alexyuzefovich.pillbox.ui.model.Type

fun List<PillData>.toPills(): List<Pill> = map { it.toPill() }

fun PillData.toPill(): Pill = Pill(
    id,
    name,
    notes,
    quantity, QuantityMetric.getBySimpleName(quantityMetric),
    dosage, DosageMetric.getBySimpleName(dosageMetric),
    bestBeforeDate,
    Type.getBySimpleName(type)
)


fun Pill.toPillData(): PillData = PillData(
    id,
    name,
    notes,
    quantity, quantityMetric.simpleName,
    dosage, dosageMetric.simpleName,
    bestBeforeDate,
    type.toString()
)