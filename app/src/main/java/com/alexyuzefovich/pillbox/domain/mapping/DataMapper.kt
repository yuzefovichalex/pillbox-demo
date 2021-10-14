package com.alexyuzefovich.pillbox.domain.mapping

import com.alexyuzefovich.pillbox.data.db.model.PillData
import com.alexyuzefovich.pillbox.ui.model.Pill

fun List<PillData>.toPills(): List<Pill> = map { it.toPill() }

fun PillData.toPill(): Pill = Pill(
    id,
    name,
    notes,
    quantity, quantityMetric,
    dosage, dosageMetric,
    bestBeforeDate,
    type.toPillType()
)

fun String.toPillType(): Pill.Type =
    when (this) {
        "tablet" -> Pill.Type.TABLET
        "capsule" -> Pill.Type.CAPSULE
        else -> Pill.Type.TABLET
    }