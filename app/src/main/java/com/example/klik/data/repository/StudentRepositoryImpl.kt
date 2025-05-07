package com.example.klik.data.repository

import com.example.klik.data.model.Student
import com.example.klik.data.remote.StudentRemoteDs
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val remote: StudentRemoteDs
) : StudentRepository {

    override fun current(): Flow<Student> =
        remote.observe(auth.currentUser!!.uid)

    override fun uid(): String = auth.currentUser?.uid.orEmpty()

    override suspend fun addClass(classId: String) {
        remote.addClass(auth.currentUser!!.uid, classId)
    }
}
