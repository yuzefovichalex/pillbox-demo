package com.alexyuzefovich.pillbox.ui.model

import androidx.annotation.DrawableRes
import com.alexyuzefovich.pillbox.R

data class Pill(
    val id: Long,
    val name: String,
    val notes: String?,
    val quantity: Int,
    val quantityMetric: String,
    val dosage: Int?,
    val dosageMetric: String?,
    val bestBeforeDate: Long,
    val type: Type
) {

    enum class Type(@DrawableRes val imageResId: Int) {
        TABLET(R.drawable.ic_tablet),
        CAPSULE(R.drawable.ic_capsule)
    }

}