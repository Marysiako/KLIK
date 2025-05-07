package com.example.klik.viewmodel.student

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.repository.ClassRepository
import com.example.klik.data.repository.StudentRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentAddClassVm @Inject constructor(
    private val studentRepo: StudentRepository,
    private val classRepo: ClassRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    /** true → pokazuje spinner i blokuje przycisk */
    var isJoining by mutableStateOf(false)
        private set

    /** komunikat dla UI; null = brak błędu */
    var errorMessage by mutableStateOf<String?>(null)
        private set

    /**
     * Próbuje dodać zalogowanego ucznia do wskazanej klasy.
     * @param classId  dokumentId w kolekcji `/classes`
     * @param onSuccess  callback do nawigacji / snackbara po udanym zapisie
     */
    fun onJoinClass(classId: String, onSuccess: () -> Unit = {}) {
        if (classId.isBlank()) {
            errorMessage = "Podaj numer klasy"
            return
        }

        viewModelScope.launch {
            isJoining = true
            errorMessage = null
            try {
                // 1) uczeń → lista klas
                studentRepo.addClass(classId)

                // 2) klasa → lista uczniów
                val uid = auth.currentUser!!.uid
                classRepo.addStudent(classId, uid)

                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Nie udało się dodać klasy"
            } finally {
                isJoining = false
            }
        }
    }
}