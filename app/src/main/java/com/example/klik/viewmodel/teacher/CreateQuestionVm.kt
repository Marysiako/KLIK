package com.example.klik.viewmodel.teacher

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.Question
import com.example.klik.data.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CreateQuestionVm @Inject constructor(
    private val questionRepo: QuestionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /** ID klasy przekazane w nawigacji */
    val classId: String = checkNotNull(savedStateHandle["classId"]) { "classId missing" }

    /** Wysyła pytanie z CZTEREMA odpowiedziami */
    fun sendQuestion(
        text: String,
        a: String,
        b: String,
        c: String,
        d: String
    ) = viewModelScope.launch {
        val q = Question(
            text         = text.trim(),
            answers      = listOf(a.trim(), b.trim(), c.trim(), d.trim()),
            answerScores = List(4) { 0 },
            fromTeacher  = true
        )
        questionRepo.create(classId, q)
    }
}
