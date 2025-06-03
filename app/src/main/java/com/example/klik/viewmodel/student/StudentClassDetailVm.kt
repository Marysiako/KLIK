package com.example.klik.viewmodel.student

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.repository.ClassRepository
import com.example.klik.viewmodel.teacher.setCounters
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class StudentClassDetailVm @Inject constructor(
    private val classRepo: ClassRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val classId: String =
        checkNotNull(savedStateHandle["classId"]) { "classId missing" }

    /** UI-state dla ekranu szczegółów klasy ucznia */
    data class UiState(
        val name: String = "",
        val id: String = "",
        val understand: Int = 0,
        val dontUnderstand: Int = 0
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            classRepo.observe(classId)
                .collect { cls ->
                    _uiState.update {
                        it.copy(
                            name             = cls.name,
                            id               = cls.id,
                            //understand       = cls.understandCount,
                            //dontUnderstand   = cls.dontUnderstandCount
                        )
                    }
                }
        }
    }

    /** Uczeń może wysłać pytanie lub reakcję do nauczyciela — metody do implementacji */

    fun incrementUnderstand() {
        viewModelScope.launch {
            val newCount = _uiState.value.understand + 1
            classRepo.setCounters(classId, newCount, _uiState.value.dontUnderstand)
        }
    }

    fun incrementDontUnderstand() {
        viewModelScope.launch {
            val newCount = _uiState.value.dontUnderstand + 1
            classRepo.setCounters(classId, _uiState.value.understand, newCount)
        }
    }

    fun sendQuestionToTeacher(questionText: String) {
        viewModelScope.launch {
            // TODO: implementacja wysłania pytania do nauczyciela
            // Możesz użyć klasy repo do zapisu pytania w bazie danych
        }
    }
}