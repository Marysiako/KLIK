import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.klik.ui.screens.LoginScreen
import com.example.klik.ui.screens.RegisterScreen
import com.example.klik.ui.screens.WelcomeScreen

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