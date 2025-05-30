package com.example.klik.ui.screens.student

import KLIKViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.klik.data.model.SchoolClass
import com.example.klik.ui.screens.ClassListElement
import com.example.klik.viewmodel.student.StudentClassListVm

//EKRAN LISTY KLAS UCZNIA Z DOLNĄ NAWILIGACJĄ
@Composable
fun StudentClassListScreen(
    viewModel: StudentClassListVm,
    onLogoutClick: () -> Unit,
    onAddClassClick: () -> Unit,
    onClassListElementClick: (String /*classId*/) -> Unit
) {
    /* Przykładowa lista przedmiotów i ID klas
    val classList = listOf(
        Pair("Matematyka", 101),
        Pair("Fizyka", 102),
        Pair("Biologia", 103)
    )
    */
    val classList = viewModel.uiState.collectAsState()
    // Kontener dla całego ekranu
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Górna część ekranu z tytułem
        Text(
            text = "Klasy",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Lista przedmiotów
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(classList.value) { schoolClass  ->
                ClassListElement(
                    subjectName = schoolClass.name,
                    classId     = schoolClass.id.toIntOrNull() ?: 0,
                    onClick     = { onClassListElementClick(schoolClass.id) }
                )
            }

        }

        // Dolna nawigacja z dwoma przyciskami
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = onLogoutClick,
                icon = {},
                label = { Text("Wyloguj") }
            )
            NavigationBarItem(
                selected = false,
                onClick = onAddClassClick,
                icon = {},
                label = { Text("Dodaj klasę") }
            )
        }
    }
}
