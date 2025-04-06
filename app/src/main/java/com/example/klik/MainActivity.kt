package com.example.klik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.klik.ui.theme.KLIKTheme
import androidx.compose.material3.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KLIKTheme {
                    AppNavigation()
            }
        }
    }
}

class KLIKViewModel : ViewModel() {
//    private val _user = MutableLiveData(dummyPatterns)
//    val usesr: LiveData<List<Pattern>> get() = _patterns

//Funkcja sprawdza czy użytkownik podał prawidłowe hasło, jeśli tak to go loguje
//    fun checkUser(name: , password: ) {
  //  }

}

@Composable
fun AppNavigation(viewModel: KLIKViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(
       // bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "welcomeScreen",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("welcomeScreen") {
                WelcomeScreen(
                    viewModel = viewModel,
                    onLoginClick = { navController.navigate("loginScreen") },
                    onRegisterClick = { navController.navigate("registerScreen") }
                )
            }
            composable("loginScreen") {
                LoginScreen(viewModel = viewModel)
            }
            composable("registerScreen") {
                RegisterScreen(viewModel = viewModel)
            }

        }
    }
}

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
//EKRAN LOGIN - tu uzytkownik loguje się na swoje konto
@Composable
fun LoginScreen(viewModel: KLIKViewModel) {
}

//EKRAN REGISTER - tu uzytkownik tworzy nowe konto
@Composable
fun RegisterScreen(viewModel: KLIKViewModel) {
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KLIKTheme {
        WelcomeScreen(
            viewModel = KLIKViewModel(),
            onLoginClick = {},
            onRegisterClick = {})
    }
}