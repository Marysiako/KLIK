package com.example.klik.viewmodel.student

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.Question
import com.example.klik.data.repository.QuestionRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class StudentReceivedQuestionVm @Inject constructor(
    private val questionRepo: QuestionRepository,
    private val firestore: FirebaseFirestore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val classId: String =
        checkNotNull(savedStateHandle["classId"]) { "classId missing" }

    val question: StateFlow<Question?> = questionRepo.observe(classId)
        .map { list ->
            list.filter { it.fromTeacher }
                .maxByOrNull { it.id.toLongOrNull() ?: 0L }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun sendAnswer(index: Int) = viewModelScope.launch {
        val q = question.value ?: return@launch
        val doc = firestore.collection("classes")
            .document(classId)
            .collection("questions")
            .document(q.id)

        firestore.runTransaction { txn ->
            val snap = txn.get(doc)

            val raw = snap.get("answerScores")

            val scores: MutableList<Long> = when (raw) {
                is List<*> -> raw.map { (it as Number).toLong() }.toMutableList()
                is Map<*, *> -> List(4) { i ->
                    (raw[i.toString()] as? Number ?: 0).toLong()
                }.toMutableList()
                else -> MutableList(4) { 0L }
            }

            if (index in 0..3) scores[index]++

            txn.update(doc, mapOf("answerScores" to scores))
        }.await()
    }
}
