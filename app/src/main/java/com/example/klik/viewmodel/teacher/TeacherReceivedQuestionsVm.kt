package com.example.klik.viewmodel.teacher

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.Question
import com.example.klik.data.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * VM listy pytań od uczniów.
 *  • nie wymaga już parametru classId (jeśli brak – zwraca pustą listę)
 */
@HiltViewModel
class TeacherReceivedQuestionsVm @Inject constructor(
    private val questionRepo: QuestionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /** classId może NIE przyjść z nawigacji ─ nie wywalamy wyjątku */
    private val classId: String? = savedStateHandle["classId"]

    private fun source(): Flow<List<Question>> =
        classId?.let { id ->
            questionRepo.observe(id).map { it.filter { q -> !q.fromTeacher } }
        } ?: flowOf(emptyList())

    /** Publiczny strumień do UI */
    val questions: StateFlow<List<Question>> = source()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}
