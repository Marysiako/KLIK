package com.example.klik.data.repository

import com.example.klik.data.model.Teacher
import kotlinx.coroutines.flow.Flow

/**
 * Interfejs operacji dostępnych dla nauczyciela.
 */
interface TeacherRepository {

    /** Bieżący nauczyciel jako Flow (profil + live-update). */
    fun current(): Flow<Teacher>

    /**
     * Po utworzeniu nowej klasy przypina jej ID
     * do listy `classIds` nauczyciela.
     */
    suspend fun createClass(schoolClassId: String)
}
