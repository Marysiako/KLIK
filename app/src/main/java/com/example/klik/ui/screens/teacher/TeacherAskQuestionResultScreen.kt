package com.example.klik.ui.screens.teacher

import KLIKViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TeacherAskQuestionResultScreen(
    viewModel: KLIKViewModel,
    onCBackToClassClick: () -> Unit,
    ) {
    val classId = 101

    // Przykładowa treść pytania i odpowiedzi (zastąpić danymi z ViewModel)
    val questionText = "Jakie piwo najlepsze?"
    val answerA = "Harnaś"
    val answerB = "Kozel"
    val answerC = "Perła"

    // Przykładowe dane - liczba osób, które zaznaczyły odpowiedzi
    val answerACount = 5
    val answerBCount = 3
    val answerCCount = 2

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Górny nagłówek
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Wyniki pytania!",
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

        // Odpowiedzi A, B, C jako teksty z liczbą osób
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Odpowiedź A
            Row(
                modifier = Modifier

                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "A: $answerA",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "$answerACount osób",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

            // Odpowiedź B
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "B: $answerB",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "$answerBCount osób",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

            // Odpowiedź C
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "C: $answerC",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "$answerCCount osób",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }

        // Dolna nawigacja
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { onCBackToClassClick },
                icon = {},
                label = { Text("Powrót") }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TeacherAskQuestionResultScreenPreview() {
    TeacherAskQuestionResultScreen(
        viewModel = KLIKViewModel(),
        onCBackToClassClick = {}
    )
}
