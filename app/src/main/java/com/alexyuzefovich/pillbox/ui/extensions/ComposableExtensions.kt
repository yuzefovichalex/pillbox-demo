package com.alexyuzefovich.pillbox.ui.extensions

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
fun ModalBottomSheetState.showFull(scope: CoroutineScope) {
    scope.launch {
        animateTo(ModalBottomSheetValue.Expanded)
    }
}

@ExperimentalMaterialApi
fun ModalBottomSheetState.hide(scope: CoroutineScope) {
    scope.launch {
        hide()
    }
}