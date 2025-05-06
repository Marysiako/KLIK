package com.example.klik.data.repository

import com.example.klik.data.model.SchoolClass
import com.example.klik.data.remote.ClassRemoteDs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClassRepositoryImpl @Inject constructor(
    private val remote: ClassRemoteDs
) : ClassRepository {

    override fun observe(id: String): Flow<SchoolClass> =
        remote.observe(id)

    override fun observeMany(ids: List<String>): Flow<List<SchoolClass>> =
        remote.observeMany(ids)

    override suspend fun create(schoolClass: SchoolClass) {
        remote.create(schoolClass)
    }

    override suspend fun addStudent(classId: String, studentId: String) {
        remote.addStudent(classId, studentId)
    }
}
