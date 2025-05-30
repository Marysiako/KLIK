package com.example.klik.viewmodel.teacher

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.SchoolClass
import com.example.klik.data.repository.ClassRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherClassDetailVm @Inject constructor(
    private val classRepo: ClassRepository,
    savedStateHandle: SavedStateHandle            // ↙ dostajemy {classId} z trasy
) : ViewModel() {

    private val classId: String =
        checkNotNull(savedStateHandle["classId"]) { "classId missing" }

    /** UI-state – wszystko, czego potrzebuje ekran */
    data class UiState(
        val name: String = "",
        val id: String = "",
        val understand: Int = 0,
        val dontUnderstand: Int = 0
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            classRepo.observe(classId)
                .collect { cls ->
                    _uiState.update {
                        it.copy(
                            name             = cls.name,
                            id               = cls.id,
                           // understand       = cls.extra<Int>("understandCount"),          TODO
                           // dontUnderstand   = cls.extra<Int>("dontUnderstandCount")       TODO
                        )
                    }
                }
        }
    }

    /** kasuje liczniki w Firestore */
    fun resetCounts() {
        viewModelScope.launch {
            classRepo.setCounters(classId, 0, 0)   // metoda pomocnicza (patrz niżej)
        }
    }
}

/* ------------------------------------------------------------- *
 *  Extension / helper dla ClassRepository – niech siedzi razem
 *  z repo (ClassRepository.kt)  albo w pliku utils, jak wolisz.
 * ------------------------------------------------------------- */
suspend fun ClassRepository.setCounters(
    classId: String,
    understand: Int,
    dontUnderstand: Int
) {
    (this as? com.example.klik.data.repository.ClassRepositoryImpl)
        ?.remote
        ?.col?.document(classId)
        ?.update(
            mapOf(
                "understandCount"     to understand,
                "dontUnderstandCount" to dontUnderstand
            )
        )
}
