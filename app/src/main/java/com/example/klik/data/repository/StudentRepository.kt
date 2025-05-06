package com.example.klik.data.repository

import com.example.klik.data.model.Student
import kotlinx.coroutines.flow.Flow

/**
 * Dostarcza dane o zalogowanym uczniu.
 * ViewModel nigdy nie komunikuje się bezpośrednio z Firebase –
 * korzysta tylko z tego interfejsu.
 */
interface StudentRepository {

    /** Strumień aktualnego profilu ucznia (live-update z Firestore). */
    fun current(): Flow<Student>

    /**
     * Dodaje identyfikator klasy do listy `classIds`
     * w dokumencie ucznia.
     */
    suspend fun addClass(classId: String)
}
