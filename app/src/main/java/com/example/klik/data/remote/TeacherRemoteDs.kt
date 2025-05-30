package com.example.klik.data.remote

import com.example.klik.data.model.Teacher
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeacherRemoteDs @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val col get() = firestore.collection("teachers")

    suspend fun create(teacher: Teacher) =
        col.document(teacher.uid).set(teacher).await()

    suspend fun get(uid: String): Teacher? =
        col.document(uid).get().await().toObject(Teacher::class.java)

    fun observe(uid: String): Flow<Teacher> = callbackFlow {
        val reg = col.document(uid).addSnapshotListener { snap, _ ->
            snap?.toObject(Teacher::class.java)?.let(::trySend)
        }
        awaitClose { reg.remove() }
    }

    suspend fun addClass(uid: String, classId: String) =
        col.document(uid).update("classIds", FieldValue.arrayUnion(classId)).await()
}
