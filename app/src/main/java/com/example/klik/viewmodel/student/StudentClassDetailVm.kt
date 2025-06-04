package com.example.klik.viewmodel.student

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.Question
import com.example.klik.data.repository.ClassRepository
import com.example.klik.data.repository.QuestionRepository
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
    private val questionRepo: QuestionRepository,   // <-- wstrzyknięty repo
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val classId: String =
        checkNotNull(savedStateHandle["classId"]) { "classId missing" }

    /* -------- UI state -------- */
    data class UiState(
        val name: String = "",
        val id: String = ""
    )
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            classRepo.observe(classId).collect { cls ->
                _uiState.update { it.copy(name = cls.name, id = cls.id) }
            }
        }
    }

    /* -------- Reakcje -------- */
    //fun incrementUnderstand()   = viewModelScope.launch { classRepo.incrementUnderstand(classId) }
    //fun incrementDontUnderstand() = viewModelScope.launch { classRepo.incrementDontUnderstand(classId) }

    /* -------- Wysyłanie pytania uczeń ➜ uczeń -------- */
    fun sendQuestion(questionText: String) = viewModelScope.launch {
        val txt = questionText.trim()
        if (txt.isEmpty()) return@launch

        val q = Question(
            text = txt,
            answers = List(4) { "" },
            answerScores = List(4) { 0 },
            fromTeacher = false          // flagę dodałeś wcześniej
        )
        questionRepo.create(classId, q)
    }
}
