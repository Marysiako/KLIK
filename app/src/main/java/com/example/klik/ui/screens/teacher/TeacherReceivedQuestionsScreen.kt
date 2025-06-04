package com.example.klik.ui.screens.teacher

import KLIKViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.klik.viewmodel.teacher.TeacherReceivedQuestionsVm

@Composable
fun TeacherReceivedQuestionsScreen(
    viewModel: KLIKViewModel,
    onBackToClassClick: () -> Unit
) {
    /** Nowy VM tylko do pytań */
    val teacherVm: TeacherReceivedQuestionsVm = hiltViewModel()
    val questionList by teacherVm.questions.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        /* Nagłówek */
        Text(
            text = "Pytania od uczniów",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        /* Lista pytań */
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(questionList) { q ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = q.text,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                }
            }
        }

        /* Dolna nawigacja */
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
