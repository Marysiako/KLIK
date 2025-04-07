package com.example.klik.ui.screens

import KLIKViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//EKRAN REGISTER - tu uzytkownik tworzy nowe konto
@Composable
fun RegisterScreen(viewModel: KLIKViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("Uczeń") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Zarejestruj się",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Pole do wpisania nazwy użytkownika
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nazwa użytkownika") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pole do wpisania hasła
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Hasło") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Wybór roli (Uczeń/Nauczyciel)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Rola: ")
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(
                selected = selectedRole == "Uczeń",
                onClick = { selectedRole = "Uczeń" }
            )
            Text("Uczeń")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = selectedRole == "Nauczyciel",
                onClick = { selectedRole = "Nauczyciel" }
            )
            Text("Nauczyciel")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Przycisk "Utwórz konto"
        Button(
            onClick = {
                // Tu wywołaj metodę rejestracji z viewModel
                // viewModel.register(username, password, selectedRole)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Utwórz konto")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        viewModel = KLIKViewModel()
    )
}