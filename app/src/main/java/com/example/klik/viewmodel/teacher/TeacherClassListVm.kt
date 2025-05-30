package com.example.klik.viewmodel.teacher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.SchoolClass
import com.example.klik.data.repository.ClassRepository
import com.example.klik.data.repository.TeacherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TeacherClassListVm @Inject constructor(
    teacherRepo: TeacherRepository,
    private val classRepo: ClassRepository
) : ViewModel() {

    /** Klasy prowadzone przez nauczyciela (live-update) */
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<List<SchoolClass>> = teacherRepo.current()
        .flatMapLatest { teacher -> classRepo.observeMany(teacher.classIds) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}
