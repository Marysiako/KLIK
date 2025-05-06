package com.example.klik.data.remote

import com.example.klik.data.model.SchoolClass
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClassRemoteDs @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val col get() = firestore.collection("classes")

    suspend fun create(schoolClass: SchoolClass) =
        col.document(schoolClass.id).set(schoolClass).await()

    suspend fun get(id: String): SchoolClass? =
        col.document(id).get().await().toObject(SchoolClass::class.java)

    fun observe(id: String): Flow<SchoolClass> = callbackFlow {
        val reg = col.document(id).addSnapshotListener { snap, _ ->
            snap?.toObject(SchoolClass::class.java)?.let(::trySend)
        }
        awaitClose { reg.remove() }
    }

    fun observeMany(ids: List<String>): Flow<List<SchoolClass>> = callbackFlow {
        if (ids.isEmpty()) {
            trySend(emptyList())
            awaitClose { }
            return@callbackFlow
        }

        // Firestore „whereIn” maks. 10 ID – tu rozbijamy, jeśli trzeba
        val chunks = ids.chunked(10)
        val listeners = chunks.map { chunk ->
            col.whereIn(FieldPath.documentId(), chunk)
                .addSnapshotListener { snap, _ ->
                    val list = snap?.toObjects(SchoolClass::class.java) ?: emptyList()
                    trySend(list)    // wysyłamy osobne części; w repo sklejone
                }
        }

        awaitClose { listeners.forEach { it.remove() } }
    }

    suspend fun addStudent(classId: String, studentId: String) =
        col.document(classId).update("studentIds", FieldValue.arrayUnion(studentId)).await()
}
