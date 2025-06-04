package com.example.klik.viewmodel.student

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.Question
import com.example.klik.data.repository.QuestionRepository
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * VM pobiera *najnowsze* pytanie nauczyciela i zapisuje odpowiedź ucznia.
 */
@HiltViewModel
class StudentReceivedQuestionVm @Inject constructor(
    private val questionRepo: QuestionRepository,
    private val firestore: FirebaseFirestore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val classId: String =
        checkNotNull(savedStateHandle["classId"]) { "classId missing" }

    /** Najnowsze pytanie nauczyciela ➜ sortujemy NUMERYCZNIE po id */
    val question: StateFlow<Question?> = questionRepo.observe(classId)
        .map { list ->
            list
                .filter { it.fromTeacher }
                .maxByOrNull { it.id.toLongOrNull() ?: 0L }   // ← tu zmiana
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    /** Uczeń klika A/B/C/D → inkrementacja answerScores[index] */
    fun sendAnswer(index: Int) = viewModelScope.launch {
        val q = question.value ?: return@launch
        firestore.collection("classes")
            .document(classId)
            .collection("questions")
            .document(q.id)
            .update("answerScores.$index", FieldValue.increment(1))
    }
}
