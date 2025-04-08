package com.example.klik.ui.screens.student

import KLIKViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.klik.ui.screens.student.StudentClassListScreen

@Composable
fun StudentClassDetailScreen(
    viewModel: KLIKViewModel,
    onIUnderstandClick: () -> Unit,
    onIDontUnderstandClick: () -> Unit,
    onSendQuestionToTeacherClick: () -> Unit,
    onBackToClassListClick: () -> Unit
) {
    // Przykładowe dane
    val subjectName = "Matematyka"
    val classId = 101
    var questionText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Nagłówek
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = subjectName,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "ID: $classId",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Prostokąty + pole na pytanie
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // NIE ROZUMIEM - czerwony
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(vertical = 30.dp)
                    .clickable { onIDontUnderstandClick }
            ) {
                Text(
                    text = "Nie rozumiem",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // ROZUMIEM - zielony
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .padding(vertical = 30.dp)
                    .clickable { onIUnderstandClick }
            ) {
                Text(
                    text = "Rozumiem",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Pole tekstowe na pytanie
            OutlinedTextField(
                value = questionText,
                onValueChange = { questionText = it },
                label = { Text("Zadaj pytanie") },
                modifier = Modifier.fillMaxWidth()
            )

            // Przycisk wysyłający pytanie
            Button(
                onClick = {
                    onSendQuestionToTeacherClick
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Wyślij pytanie")
            }
        }

        // Dolna nawigacja
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { onBackToClassListClick },
                icon = {},
                label = { Text("Powrót do klas") }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StudentClassDetailScreenPreview() {
    StudentClassDetailScreen(
        viewModel = KLIKViewModel(),
        onIUnderstandClick = {},
        onIDontUnderstandClick = {},
        onSendQuestionToTeacherClick = {},
        onBackToClassListClick = {}
    )
}