package com.example.klik.data.remote

import com.example.klik.data.model.Student
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRemoteDs @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val col get() = firestore.collection("students")

    suspend fun create(student: Student) =
        col.document(student.uid).set(student).await()

    suspend fun get(uid: String): Student? =
        col.document(uid).get().await().toObject(Student::class.java)

    fun observe(uid: String): Flow<Student> = callbackFlow {
        val reg = col.document(uid).addSnapshotListener { snap, _ ->
            snap?.toObject(Student::class.java)?.let(::trySend)
        }
        awaitClose { reg.remove() }
    }

    suspend fun addClass(uid: String, classId: String) =
        col.document(uid).update("classIds", FieldValue.arrayUnion(classId)).await()

    suspend fun removeClass(uid: String, classId: String) =
        col.document(uid).update("classIds", FieldValue.arrayRemove(classId)).await()
}
