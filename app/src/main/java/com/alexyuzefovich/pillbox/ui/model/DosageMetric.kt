package com.alexyuzefovich.pillbox.ui.model

enum class DosageMetric(val simpleName: String) {
    MILLIGRAMS("mg"),
    GRAMS("g");

    companion object {
        fun simpleNames(): List<String> = values().map { it.simpleName }

        fun getBySimpleName(simpleName: String): DosageMetric =
            values().find { it.simpleName == simpleName } ?: MILLIGRAMS
    }
}