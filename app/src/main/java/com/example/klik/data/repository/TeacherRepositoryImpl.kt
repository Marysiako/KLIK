package com.example.klik.data.repository

import com.example.klik.data.model.Teacher
import com.example.klik.data.remote.TeacherRemoteDs
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeacherRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val remote: TeacherRemoteDs
) : TeacherRepository {

    override fun current(): Flow<Teacher> =
        remote.observe(auth.currentUser!!.uid)

    override suspend fun createClass(schoolClassId: String) {
        remote.addClass(auth.currentUser!!.uid, schoolClassId)
    }
}
