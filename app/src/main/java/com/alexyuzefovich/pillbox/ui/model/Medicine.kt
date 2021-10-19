package com.alexyuzefovich.pillbox.ui.model

data class Medicine(
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

        val EMPTY: Medicine
            get() = Medicine(
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