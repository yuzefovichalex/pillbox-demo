package com.alexyuzefovich.pillbox.ui.model

import androidx.annotation.DrawableRes
import com.alexyuzefovich.pillbox.R

enum class Type(
    val simpleName: String,
    @DrawableRes val imageResId: Int
) {
    TABLET("tablet", R.drawable.ic_tablet),
    CAPSULE("capsule", R.drawable.ic_capsule),
    INHALER("inhaler", R.drawable.ic_tablet);

    companion object {
        fun simpleNames(): List<String> = values().map { it.simpleName }

        fun getBySimpleName(simpleName: String): Type =
            values().find { it.simpleName == simpleName } ?: TABLET
    }

    override fun toString(): String = name
}