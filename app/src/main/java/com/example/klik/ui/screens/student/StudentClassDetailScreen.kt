package com.example.klik.ui.screens.student

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.klik.viewmodel.student.StudentClassDetailVm

@Composable
fun StudentClassDetailScreen(
    viewModel: StudentClassDetailVm,
    onIUnderstandClick: () -> Unit,
    onIDontUnderstandClick: () -> Unit,
    onBackToClassListClick: () -> Unit,
    onGoToQuestionFromTeacher: () -> Unit
) {
    val ui by viewModel.uiState.collectAsState()
    var questionText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        /* ───── Nagłówek ───── */
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = ui.name.ifBlank { "Klasa" },
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text("ID: ${ui.id}", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(Modifier.height(5.dp))

        /* ───── Główna kolumna ───── */
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // NIE ROZUMIEM
            Box(
                Modifier.fillMaxWidth()
                    .background(Color.Red)
                    .padding(vertical = 30.dp)
                    .clickable {
                        onIDontUnderstandClick()
                        //viewModel.incrementDontUnderstand()
                    }
            ) { Text("Nie rozumiem", color = Color.White, modifier = Modifier.align(Alignment.Center)) }

            // ROZUMIEM
            Box(
                Modifier.fillMaxWidth()
                    .background(Color.Green)
                    .padding(vertical = 30.dp)
                    .clickable {
                        onIUnderstandClick()
                        //viewModel.incrementUnderstand()
                    }
            ) { Text("Rozumiem", color = Color.White, modifier = Modifier.align(Alignment.Center)) }

            Spacer(Modifier.height(32.dp))

            // Pole tekstowe
            OutlinedTextField(
                value = questionText,
                onValueChange = { questionText = it },
                label = { Text("Zadaj pytanie") },
                modifier = Modifier.fillMaxWidth()
            )

            // Wyślij pytanie (uczeń ➜ uczeń)
            Button(
                onClick = {
                    viewModel.sendQuestion(questionText)
                    questionText = ""
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) { Text("Wyślij pytanie") }

            // Pozostawiony przycisk (jeśli kiedyś wykorzystasz)
            Button(
                onClick = onGoToQuestionFromTeacher,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) { Text("Pytanie od nauczyciela") }
        }

        /* ───── Dolna nawigacja ───── */
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = onBackToClassListClick,
                icon = {},
                label = { Text("Powrót do klas") }
            )
        }
    }
}
