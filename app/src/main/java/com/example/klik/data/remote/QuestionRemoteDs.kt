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

    /** Zapisuje pytanie w kolekcji `/classes/{classId}/questions` */
    suspend fun create(classId: String, q: Question) {
        firestore.collection("classes")
            .document(classId)
            .collection("questions")
            .add(q)
            .await()
    }

    /** Live-stream pytań z danej klasy (uczniowskich + nauczycielskich) */
    fun observe(classId: String): Flow<List<Question>> = callbackFlow {
        val reg = firestore.collection("classes")
            .document(classId)
            .collection("questions")
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    close(err)
                    return@addSnapshotListener
                }
                trySend(snap?.toObjects(Question::class.java) ?: emptyList())
            }
        awaitClose { reg.remove() }
    }
}
