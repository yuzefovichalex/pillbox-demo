package com.alexyuzefovich.pillbox.ui.model

data class Pill(
    val id: Long,
    val name: String,
    val notes: String?,
    val quantity: Int,
    val quantityMetric: QuantityMetric,
    val dosage: Int?,
    val dosageMetric: DosageMetric,
    val bestBeforeDate: Long,
    val type: Type
) {

    companion object {
        const val NO_ID = -1L

        val EMPTY: Pill
            get() = Pill(
                NO_ID,
                "",
                null,
                0, QuantityMetric.PIECES,
                null, DosageMetric.MILLIGRAMS,
                0L,
                Type.TABLET
            )
    }

}