package com.example.klik.data.repository

import com.example.klik.data.model.SchoolClass
import kotlinx.coroutines.flow.Flow

/**
 * Repozytorium do zarządzania encją SchoolClass.
 */
interface ClassRepository {

    /** Obserwacja pojedynczej klasy. */
    fun observe(id: String): Flow<SchoolClass>

    /**
     * Obserwacja wielu klas jednocześnie (lista ID).
     * Implementacja łączy chunk-owane zapytania Firestore
     * w jedną listę.
     */
    fun observeMany(ids: List<String>): Flow<List<SchoolClass>>

    /** Tworzy nową klasę (teacherId musi być już ustawione). */
    suspend fun create(schoolClass: SchoolClass)

    /** Dopisuje ucznia do `studentIds` klasy. */
    suspend fun addStudent(classId: String, studentId: String)
}
