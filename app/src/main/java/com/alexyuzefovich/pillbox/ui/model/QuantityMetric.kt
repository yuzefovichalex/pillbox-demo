package com.alexyuzefovich.pillbox.ui.model

enum class QuantityMetric(val simpleName: String) {
    PIECES("pcs"),
    MILLILITERS("ml");

    companion object {
        fun simpleNames(): List<String> = values().map { it.simpleName }

        fun getBySimpleName(simpleName: String): QuantityMetric =
            values().find { it.simpleName == simpleName } ?: PIECES
    }
}