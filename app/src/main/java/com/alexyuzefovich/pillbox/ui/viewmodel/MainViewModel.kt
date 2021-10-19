package com.alexyuzefovich.pillbox.ui.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alexyuzefovich.pillbox.domain.repository.MedicineRepository
import com.alexyuzefovich.pillbox.ui.model.BottomSheetVisibilityState
import com.alexyuzefovich.pillbox.ui.model.MedicineState
import com.alexyuzefovich.pillbox.ui.model.Medicine
import com.alexyuzefovich.pillbox.ui.model.mapping.toPill
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class MainViewModel(
    private val medicineRepository: MedicineRepository
) : ViewModel() {

    private val _pills: MutableStateFlow<List<Medicine>> = MutableStateFlow(emptyList())
    val pills: StateFlow<List<Medicine>> = _pills

    val bottomSheetVisibilityState: MutableState<BottomSheetVisibilityState> =
        mutableStateOf(BottomSheetVisibilityState.CLOSED)

    val isBottomSheetShown: Boolean
        get() = bottomSheetVisibilityState.value == BottomSheetVisibilityState.OPENED

    val currentMedicineState: MutableState<MedicineState> = mutableStateOf(MedicineState.EMPTY)

    fun loadPills() {
        viewModelScope.launch {
            medicineRepository.getAllPills().collect {
                _pills.value = it
            }
        }
    }

    fun startMedicineCreation() {
        currentMedicineState.value = MedicineState.EMPTY
        bottomSheetVisibilityState.value = BottomSheetVisibilityState.OPENED
    }

    fun openMedicineDetails(medicine: Medicine) {
        currentMedicineState.value = MedicineState.fromPill(medicine)
        bottomSheetVisibilityState.value = BottomSheetVisibilityState.OPENED
    }

    fun closeBottomSheet() {
        bottomSheetVisibilityState.value = BottomSheetVisibilityState.CLOSED
    }

    fun saveMedicine(medicineState: MedicineState) {
        val savingPill = medicineState.toPill()
        val readyToSavePill = if (savingPill.id == Medicine.NO_ID) {
            savingPill.copy(id = generateId())
        } else {
            savingPill
        }

        viewModelScope.launch {
            medicineRepository.savePill(readyToSavePill)
        }
    }

    private fun generateId(): Long = System.currentTimeMillis()


    class Factory(
        private val medicineRepository: MedicineRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(medicineRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}