package com.example.klik.data.repository

import com.example.klik.data.model.Question
import com.example.klik.data.remote.QuestionRemoteDs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepositoryImpl @Inject constructor(
    private val remote: QuestionRemoteDs
) : QuestionRepository {

    override suspend fun create(classId: String, question: Question) {
        remote.create(classId, question)
    }

    override fun observe(classId: String): Flow<List<Question>> =
        remote.observe(classId)
}
