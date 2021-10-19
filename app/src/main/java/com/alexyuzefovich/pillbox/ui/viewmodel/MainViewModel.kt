package com.alexyuzefovich.pillbox.ui.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alexyuzefovich.pillbox.domain.repository.PillRepository
import com.alexyuzefovich.pillbox.ui.model.BottomSheetVisibilityState
import com.alexyuzefovich.pillbox.ui.model.MedicineState
import com.alexyuzefovich.pillbox.ui.model.Pill
import com.alexyuzefovich.pillbox.ui.model.mapping.toPill
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class MainViewModel(
    private val pillRepository: PillRepository
) : ViewModel() {

    private val _pills: MutableStateFlow<List<Pill>> = MutableStateFlow(emptyList())
    val pills: StateFlow<List<Pill>> = _pills

    val bottomSheetVisibilityState: MutableState<BottomSheetVisibilityState> =
        mutableStateOf(BottomSheetVisibilityState.Closed)

    val isBottomSheetShown: Boolean
        get() = bottomSheetVisibilityState.value == BottomSheetVisibilityState.Opened

    val currentMedicineState: MutableState<MedicineState> = mutableStateOf(MedicineState.EMPTY)

    fun loadPills() {
        viewModelScope.launch {
            pillRepository.getAllPills().collect {
                _pills.value = it
            }
        }
    }

    fun startMedicineCreation() {
        currentMedicineState.value = MedicineState.EMPTY
        bottomSheetVisibilityState.value = BottomSheetVisibilityState.Opened
    }

    fun openMedicineDetails(pill: Pill) {
        currentMedicineState.value = MedicineState.fromPill(pill)
        bottomSheetVisibilityState.value = BottomSheetVisibilityState.Opened
    }

    fun closeBottomSheet() {
        bottomSheetVisibilityState.value = BottomSheetVisibilityState.Closed
    }

    fun saveMedicine(medicineState: MedicineState) {
        val savingPill = medicineState.toPill()
        val readyToSavePill = if (savingPill.id == Pill.NO_ID) {
            savingPill.copy(id = generateId())
        } else {
            savingPill
        }

        viewModelScope.launch {
            pillRepository.savePill(readyToSavePill)
        }
    }

    private fun generateId(): Long = System.currentTimeMillis()


    class Factory(
        private val pillRepository: PillRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(pillRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}