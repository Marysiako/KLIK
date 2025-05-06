package com.example.klik.ui.screens.teacher

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.klik.ui.screens.ClassListElement

//EKRAN LISTY KLAS NAUCZYCIELA Z DOLNĄ NAWILIGACJĄ
@Composable
fun TeacherClassListScreen(
    viewModel: KLIKViewModel,
    onLogoutClick: () -> Unit,
    onCreateClassClick: () -> Unit,
    onClassListElementClick: () -> Unit
) {
    // Przykładowa lista przedmiotów i ID klas
    val classList = listOf(
        Pair("Matematyka", 101),
        Pair("Fizyka", 102),
        Pair("Biologia", 103)
    )

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
            modifier = Modifier.weight(1f) // Sprawia, że lista zajmuje dostępne miejsce
        ) {
            items(classList) { (subjectName, classId) ->
                ClassListElement(viewModel = viewModel, subjectName = subjectName, classId = classId)
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
                onClick = onCreateClassClick,
                icon = {},
                label = { Text("Utwórz klasę") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherClassListScreenPreview() {
    TeacherClassListScreen(
        viewModel = KLIKViewModel(),
        onLogoutClick = {},
        onCreateClassClick = {},
        onClassListElementClick = {}
    )
}

