package com.example.klik.ui.screens.teacher

import KLIKViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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


@Composable
fun TeacherReceivedQuestionsScreen(
    viewModel: KLIKViewModel,
    onBackToClassClick: () -> Unit
    ) {
    // Przykładowa lista przedmiotów i ID klas
    val questionsList = listOf(
        Pair("Czy kaczki sikają?", 1),
        Pair("Jak sie robi pomidorową?", 2),
        Pair("Student chce piwo, jakis losowy dłuższy tekst scooby dooby doo ", 3)
    )

    // Kontener dla całego ekranu
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Górna część ekranu z tytułem
        Text(
            text = "Pytania",
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
            items(questionsList) { (questionText, questionId) ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable {
                            /* USUN PYTANIE ID Z BAZY*/
                        }
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                        .padding(16.dp)
                ) {
                    Text(
                        text = questionText,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Dolna nawigacja z dwoma przyciskami
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = onBackToClassClick,
                icon = {},
                label = { Text("Powrót") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherReceivedQuestionsScreenPreview() {
    TeacherReceivedQuestionsScreen(
        viewModel = KLIKViewModel(),
        onBackToClassClick = {}
    )
}