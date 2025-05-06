package com.example.klik.data.repository

import com.example.klik.data.model.Question
import kotlinx.coroutines.flow.Flow

/**
 * Repozytorium obsługujące pytania przypisane do klas.
 */
interface QuestionRepository {

    /** Zapisuje nowe pytanie w podkolekcji `/classes/{id}/questions`. */
    suspend fun create(classId: String, question: Question)

    /** Strumień pytań dla wskazanej klasy (live-update). */
    fun observe(classId: String): Flow<List<Question>>
}
