package com.example.klik.ui.screens.teacher

import KLIKViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.klik.viewmodel.teacher.CreateQuestionVm

@Composable
fun TeacherAskQuestionScreen(
    viewModel: KLIKViewModel,
    onSendToStudentsClick: () -> Unit,
    onBackToClassClick: () -> Unit
) {
    val askVm: CreateQuestionVm = hiltViewModel()
    val classId = askVm.classId

    var questionText by remember { mutableStateOf("") }
    var answerA      by remember { mutableStateOf("") }
    var answerB      by remember { mutableStateOf("") }
    var answerC      by remember { mutableStateOf("") }
    var answerD      by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        /* ───── Nagłówek ───── */
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Zadaj pytanie",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text("ID: $classId", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(Modifier.height(16.dp))

        /* ───── Pola tekstowe ───── */
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = questionText,
                onValueChange = { questionText = it },
                label = { Text("Treść pytania") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                maxLines = 5
            )
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
            OutlinedTextField(
                value = answerD,
                onValueChange = { answerD = it },
                label = { Text("Odpowiedź D") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        /* ───── Przyciski ───── */
        Button(
            onClick = {
                askVm.sendQuestion(questionText, answerA, answerB, answerC, answerD)
                questionText = ""; answerA = ""; answerB = ""; answerC = ""; answerD = ""
                onSendToStudentsClick()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text("Wyślij do uczniów")
        }

        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick  = onBackToClassClick,
                icon     = {},
                label    = { Text("Powrót") }
            )
        }
    }
}
