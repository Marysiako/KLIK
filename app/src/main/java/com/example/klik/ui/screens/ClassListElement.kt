package com.example.klik.ui.screens

import KLIKViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ELEMENT LISTY KLAS - zawiera nazwe i numer id
@Composable
fun ClassListElement(viewModel: KLIKViewModel, subjectName: String, classId: Int) {
    // Kontener, który będzie zawierał nazwę przedmiotu oraz numer ID klasy
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween, // Elementy będą wyświetlane po dwóch stronach
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tekst z nazwą przedmiotu
        Text(
            text = subjectName,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f) // Umożliwia rozciągnięcie elementu na całą szerokość
        )

        // Tekst z numerem ID klasy
        Text(
            text = "ID: $classId",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp) // Dodajemy odstęp między elementami
        )
    }
}