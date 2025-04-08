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
fun TeacherCreateClassScreen(
    viewModel: KLIKViewModel,
    onCreateClassClick: () -> Unit,
    onBackToClassListClick: () -> Unit
    ) {
    // Pamiętane stany dla pól tekstowych
    var subjectName by remember { mutableStateOf("") }
    var subjectID by remember { mutableStateOf("") }    //!!!BEDZIE TRZEBA ZMIENIC NA INT BO OutlinedTextField przyjmuje tylko string!!!!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Górny nagłówek
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Utwórz Klase",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Pola tekstowe
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Duże pole na treść pytania
            OutlinedTextField(
                value = subjectName,
                onValueChange = { subjectName = it },
                label = { Text("Nazwa klasy") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                maxLines = 5
            )

            // Odpowiedzi A, B, C
            OutlinedTextField(
                value = subjectID,
                onValueChange = { subjectID = it },
                label = { Text("Numer ID") },
                modifier = Modifier.fillMaxWidth()
            )
        }


        // Przycisk resetujący
        Button(
            onClick = {
                onCreateClassClick
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text("Utwórz")
        }

        // Dolna nawigacja
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { onBackToClassListClick},
                icon = {},
                label = { Text("Powrót do klas") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherCreateClassScreenPreview() {
    TeacherCreateClassScreen(
        viewModel = KLIKViewModel(),
        onCreateClassClick = {},
        onBackToClassListClick = {})
}