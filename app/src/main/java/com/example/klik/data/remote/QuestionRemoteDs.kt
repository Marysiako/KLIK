package com.example.klik.data.remote

import com.example.klik.data.model.Question
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class QuestionRemoteDs @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    suspend fun create(classId: String, q: Question) {
        val docId  = System.currentTimeMillis().toString()   // tylko timestamp

        firestore.collection("classes")
            .document(classId)
            .collection("questions")
            .document(docId)                                 // dokument o tym ID
            .set(q.copy(id = docId))                         // zapis danych (1 raz)
            .await()
    }

    /**  STREAM pytań danej klasy (uczeń lub nauczyciel)                      */
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
