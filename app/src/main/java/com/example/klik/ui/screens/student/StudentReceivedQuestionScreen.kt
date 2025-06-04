package com.example.klik.ui.screens.student

import KLIKViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.klik.viewmodel.student.StudentReceivedQuestionVm

@Composable
fun StudentReceivedQuestionScreen(
    viewModel: KLIKViewModel,
    onAnswerAClick: () -> Unit,
    onAnswerBClick: () -> Unit,
    onAnswerCClick: () -> Unit,
    onAnswerDClick: () -> Unit,
    onBackToClassDetailClick: () -> Unit
) {
    val recvVm: StudentReceivedQuestionVm = hiltViewModel()
    val q by recvVm.question.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        /* ───── NAGŁÓWEK ───── */
        Text(
            text = "Pytanie od nauczyciela",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        /* ───── TREŚĆ PYTANIA ───── */
        if (q == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Text(
                text = q!!.text,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(32.dp))

            /* ───── CZTERY ODPOWIEDZI ───── */
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(4) { i ->
                    val label = q!!.answers.getOrNull(i).orEmpty()
                    Button(
                        onClick = {
                            recvVm.sendAnswer(i)
                            when (i) {
                                0 -> onAnswerAClick()
                                1 -> onAnswerBClick()
                                2 -> onAnswerCClick()
                                3 -> onAnswerDClick()
                            }
                        },
                        enabled = label.isNotBlank(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("${'A' + i}: ${if (label.isNotBlank()) label else "—"}")
                    }
                }
            }
        }

        /* ───── DOLNA NAWIGACJA ───── */
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
