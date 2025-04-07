package com.example.klik.ui.screens.teacher

import KLIKViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TeacherAskQuestionScreen(viewModel: KLIKViewModel) {
    val classId = 101

    // Pamiętane stany dla pól tekstowych
    var questionText by remember { mutableStateOf("") }
    var answerA by remember { mutableStateOf("") }
    var answerB by remember { mutableStateOf("") }
    var answerC by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Górny nagłówek
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Zadaj pytanie",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "ID: $classId",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pola tekstowe
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Duże pole na treść pytania
            OutlinedTextField(
                value = questionText,
                onValueChange = { questionText = it },
                label = { Text("Treść pytania") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                maxLines = 5
            )

            // Odpowiedzi A, B, C
            OutlinedTextField(
                value = answerA,
                onValueChange = { answerA = it },
                label = { Text("Odpowiedź A") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = answerB,
                onValueChange = { answerB = it },
                label = { Text("Odpowiedź B") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = answerC,
                onValueChange = { answerC = it },
                label = { Text("Odpowiedź C") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Przycisk wysyłający pytanie
        Button(
            onClick = {
                // Tu logika wysłania pytania z odpowiedziami
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text("Wyślij do uczniów")
        }

        // Dolna nawigacja
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { /* Powrót */ },
                icon = {},
                label = { Text("Powrót") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherAskQuestionScreenPreview() {
    TeacherAskQuestionScreen(viewModel = KLIKViewModel())
}
