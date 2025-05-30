package com.example.klik.data.repository

import com.example.klik.data.model.SchoolClass
import com.example.klik.data.remote.ClassRemoteDs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.runningFold
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClassRepositoryImpl @Inject constructor(
    val remote: ClassRemoteDs
) : ClassRepository {
    override suspend fun get(id: String): SchoolClass? =
        remote.get(id)
    override fun observe(id: String): Flow<SchoolClass> =
        remote.observe(id)

    override fun observeMany(ids: List<String>): Flow<List<SchoolClass>> =
        remote.observeMany(ids)
            .runningFold(emptyMap<String, SchoolClass>()) { acc, chunk ->
                acc + chunk.associateBy { it.id }
            }
            .map { it.values.toList() }

    override suspend fun create(schoolClass: SchoolClass) {
        remote.create(schoolClass)
    }

    override suspend fun addStudent(classId: String, studentId: String) {
        remote.addStudent(classId, studentId)
    }
}
