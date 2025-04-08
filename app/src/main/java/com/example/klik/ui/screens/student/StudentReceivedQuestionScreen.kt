package com.example.klik.ui.screens.student

import KLIKViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StudentReceivedQuestionScreen(
    viewModel: KLIKViewModel,
    onAnswerAClick: () -> Unit,
    onAnswerBClick: () -> Unit,
    onAnswerCClick: () -> Unit,
    onBackToClassDetailClick: () -> Unit
) {
    val classId = 101

    // Przykładowa treść pytania i odpowiedzi (zastąpić danymi z ViewModel)
    val questionText = "Jakie piwo najlepsze?"
    val answerA = "Harnaś"
    val answerB = "Kozel"
    val answerC = "Perła"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Górny nagłówek
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Pytanie!",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Treść pytania
        Text(
            text = questionText,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Odpowiedzi A, B, C jako przyciski
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onAnswerAClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("A: $answerA")
            }

            Button(
                onClick = onAnswerBClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("B: $answerB")
            }

            Button(
                onClick = onAnswerCClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("C: $answerC")
            }
        }

        // Dolna nawigacja
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = onBackToClassDetailClick,
                icon = {},
                label = { Text("Powrót") }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StudentReceivedQuestionScreenPreview() {
    StudentReceivedQuestionScreen(
        viewModel = KLIKViewModel(),
        onAnswerAClick = {},
        onAnswerBClick = {},
        onAnswerCClick = {},
        onBackToClassDetailClick = {}
    )
}
