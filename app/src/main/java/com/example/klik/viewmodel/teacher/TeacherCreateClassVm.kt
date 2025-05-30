package com.example.klik.viewmodel.teacher

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.SchoolClass
import com.example.klik.data.repository.ClassRepository
import com.example.klik.data.repository.TeacherRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherCreateClassVm @Inject constructor(
    private val classRepo:   ClassRepository,
    private val teacherRepo: TeacherRepository,
    private val auth:        FirebaseAuth
) : ViewModel() {

    var isCreating   = mutableStateOf(false);   private set
    var errorMessage = mutableStateOf<String?>(null); private set

    // tworzy klasę i dopisuje ją do profilu nauczyciela
    fun onCreateClass(name: String, id: String, onSuccess: () -> Unit = {}) {
        if (name.isBlank() || id.isBlank()) {
            errorMessage.value = "Wpisz nazwę i numer ID"
            return
        }

        viewModelScope.launch {
            isCreating.value = true
            errorMessage.value = null
            try {
                val newClass = SchoolClass(
                    id         = id,
                    name       = name,
                    teacherId  = auth.currentUser!!.uid
                )

                classRepo.create(newClass)
                teacherRepo.createClass(id)              // dopisz klasę do nauczyciela
                onSuccess()
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isCreating.value = false
            }
        }
    }
}
