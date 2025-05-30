package com.example.klik.viewmodel.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.SchoolClass
import com.example.klik.data.repository.ClassRepository
import com.example.klik.data.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class StudentClassListVm @Inject constructor(
    studentRepo: StudentRepository,
    private val classRepo: ClassRepository
) : ViewModel() {

    /** Lista klas widoczna w UI */
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<List<SchoolClass>> = studentRepo.current()
        .flatMapLatest { student ->
            classRepo.observeMany(student.classIds)
        }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}
