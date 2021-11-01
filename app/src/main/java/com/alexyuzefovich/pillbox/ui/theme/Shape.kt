package com.alexyuzefovich.pillbox.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(50),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

val BottomSheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)