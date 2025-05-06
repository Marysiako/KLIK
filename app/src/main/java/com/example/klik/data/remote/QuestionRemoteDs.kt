package com.example.klik.data.remote

import com.example.klik.data.model.Question
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRemoteDs @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private fun questionsCol(classId: String) =
        firestore.collection("classes").document(classId).collection("questions")

    suspend fun create(classId: String, question: Question) =
        questionsCol(classId).document(question.id).set(question).await()

    fun observe(classId: String): Flow<List<Question>> = callbackFlow {
        val reg = questionsCol(classId)
            .addSnapshotListener { snap, _ ->
                val list = snap?.toObjects(Question::class.java) ?: emptyList()
                trySend(list)
            }
        awaitClose { reg.remove() }
    }
}
