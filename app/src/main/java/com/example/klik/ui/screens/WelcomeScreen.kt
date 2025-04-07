package com.example.klik.ui.screens

import KLIKViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//EKRAN WELCOME - tu użytkownik wybiera czy sie loguje na istniejace czy tworzy nowe konto
@Composable
fun WelcomeScreen(
    viewModel: KLIKViewModel,
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Witaj w KLIK - Komunikacja Lekcyjna, Interaktywna Klasa",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onLoginClick) {
            Text("Zaloguj się")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRegisterClick) {
            Text("Zarejestruj się")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
            viewModel = KLIKViewModel(),
            onLoginClick = {},
            onRegisterClick = {}
        )
}