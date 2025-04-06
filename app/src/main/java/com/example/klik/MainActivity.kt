package com.example.klik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.klik.ui.theme.KLIKTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
    //fun login(username, password){}

    //Funkcja sprawdza czy nazwa uzytkownika jest dostepna, jesli tak to tworzy uzytkownika i na niego loguje
    //register(username, password, selectedRole)

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
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Zaloguj się",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Pole do wpisania loginu
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Login") },
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

        Spacer(modifier = Modifier.height(32.dp))

        // Przycisk "Zaloguj"
        Button(
            onClick = {
                // Tu można wywołać metodę logowania z viewModel
                // viewModel.login(username, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Zaloguj")
        }
    }
}

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
@Composable
fun TeacherScreen(viewModel: KLIKViewModel) {

}

//EKRAN LISTY KLAS NAUCZYCIELA Z DOLNĄ NAWILIGACJĄ
@Composable
fun TeacherClassListScreen(viewModel: KLIKViewModel) {
    // Przykładowa lista przedmiotów i ID klas
    val classList = listOf(
        Pair("Matematyka", 101),
        Pair("Fizyka", 102),
        Pair("Biologia", 103)
    )

    // Kontener dla całego ekranu
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Górna część ekranu z tytułem
        Text(
            text = "Klasy",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Lista przedmiotów
        LazyColumn(
            modifier = Modifier.weight(1f) // Sprawia, że lista zajmuje dostępne miejsce
        ) {
            items(classList) { (subjectName, classId) ->
                ClassListElement(viewModel = viewModel, subjectName = subjectName, classId = classId)
            }
        }

        // Dolna nawigacja z dwoma przyciskami
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { /* Wyloguj */ },
                icon = {},
                label = { Text("Wyloguj") }
            )
            NavigationBarItem(
                selected = false,
                onClick = { /* Utwórz klasę */ },
                icon = {},
                label = { Text("Utwórz klasę") }
            )
        }
    }
}

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
@Composable
fun TeacherClassDetailScreen(viewModel: KLIKViewModel) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KLIKTheme {
//        WelcomeScreen(
//            viewModel = KLIKViewModel(),
//            onLoginClick = {},
//            onRegisterClick = {}
//        )

//        ClassListElement(
//            viewModel= KLIKViewModel(),
//            "matematyka",
//            12345
//        )
        TeacherClassListScreen(viewModel= KLIKViewModel())
    }
}