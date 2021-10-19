package com.alexyuzefovich.pillbox.ui.model

sealed class BottomSheetVisibilityState {
    object Closed : BottomSheetVisibilityState()
    object Opened : BottomSheetVisibilityState()
}