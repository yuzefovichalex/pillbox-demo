package com.alexyuzefovich.pillbox.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alexyuzefovich.pillbox.domain.repository.PillRepository
import com.alexyuzefovich.pillbox.ui.model.Pill
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val pillRepository: PillRepository
) : ViewModel() {

    private val _pills: MutableStateFlow<List<Pill>> = MutableStateFlow(emptyList())
    val pills: StateFlow<List<Pill>> = _pills

    fun loadPills() {
        viewModelScope.launch {
            pillRepository.getAllPills().collect {
                _pills.value = it
            }
        }
    }

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