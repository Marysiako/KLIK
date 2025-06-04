import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.klik.ui.screens.*
import com.example.klik.ui.screens.student.*
import com.example.klik.ui.screens.teacher.*
import com.example.klik.viewmodel.*
import com.example.klik.viewmodel.student.*
import com.example.klik.viewmodel.teacher.*

@Composable
fun AppNavigation(viewModel: KLIKViewModel = viewModel()) {

    val navController = rememberNavController()

    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "welcomeScreen",
            modifier = Modifier.padding(paddingValues)
        ) {

            /* ───── WELCOME / AUTH ───── */
            composable("welcomeScreen") {
                WelcomeScreen(
                    viewModel,
                    onLoginClick    = { navController.navigate("loginScreen") },
                    onRegisterClick = { navController.navigate("registerScreen") }
                )
            }
            composable("loginScreen") {
                val vm: AuthViewModel = hiltViewModel()
                LoginScreen(
                    viewModel = vm,
                    onLoggedIn = { role ->
                        when (role) {
                            "Uczeń"     -> navController.navigate("studentClassListScreen") { popUpTo("login") { inclusive = true } }
                            "Nauczyciel" -> navController.navigate("teacherClassListScreen") { popUpTo("login") { inclusive = true } }
                        }
                    },
                    bypassLoginStudentClick = { navController.navigate("studentClassListScreen") },
                    bypassLoginTeacherClick = { navController.navigate("teacherClassListScreen") }
                )
            }
            composable("registerScreen") {
                val vm: AuthViewModel = hiltViewModel()
                RegisterScreen(
                    viewModel = vm,
                    onRegistered = { role ->
                        when (role) {
                            "Uczeń"     -> navController.navigate("studentClassListScreen") { popUpTo("login") { inclusive = true } }
                            "Nauczyciel" -> navController.navigate("teacherClassListScreen") { popUpTo("login") { inclusive = true } }
                        }
                    }
                )
            }

            /* ───── TEACHER LISTA KLAS ───── */
            composable("teacherClassListScreen") {
                TeacherClassListScreen(
                    viewModel = hiltViewModel(),
                    onLogoutClick        = { navController.navigate("welcomeScreen") { popUpTo("teacherClassList") { inclusive = true } } },
                    onCreateClassClick   = { navController.navigate("teacherCreateClassScreen") },
                    onClassListElementClick = { classId ->
                        navController.navigate("teacherClassDetailScreen/$classId")
                    }
                )
            }

            /* ───── TEACHER – SZCZEGÓŁ KLASY ───── */
            composable(
                route = "teacherClassDetailScreen/{classId}",
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                val vm: TeacherClassDetailVm = hiltViewModel(backStackEntry)
                val classId = backStackEntry.arguments?.getString("classId") ?: ""
                TeacherClassDetailScreen(
                    viewModel = vm,
                    onBackToClassListClick = { navController.navigate("teacherClassListScreen") },
                    onAskStudentsClick     = { navController.navigate("teacherAskQuestionScreen/$classId") },
                    onReceivedQuestionClick= { navController.navigate("teacherReceivedQuestionsScreen/$classId") }
                )
            }

            /* ───── TEACHER – TWORZENIE KLASY ───── */
            composable("teacherCreateClassScreen") {
                TeacherCreateClassScreen(
                    viewModel = hiltViewModel(),
                    onBackToClassListClick = { navController.popBackStack() }
                )
            }

            /* ───── TEACHER – ZADAWANIE PYTANIA ───── */
            composable(
                route = "teacherAskQuestionScreen/{classId}",
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                val classId = backStackEntry.arguments?.getString("classId") ?: ""
                TeacherAskQuestionScreen(
                    viewModel = viewModel,
                    onBackToClassClick = { navController.navigate("teacherClassDetailScreen/$classId") },
                    onSendToStudentsClick = { /* TODO */ }
                )
            }

            /* ───── TEACHER – ODEBRANE PYTANIA ───── */
            composable(
                route = "teacherReceivedQuestionsScreen/{classId}",
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                TeacherReceivedQuestionsScreen(
                    viewModel = viewModel,
                    onBackToClassClick = { navController.popBackStack() }
                )
            }

            /* ───── STUDENT LISTA KLAS ───── */
            composable("studentClassListScreen") {
                val vm: StudentClassListVm = hiltViewModel()
                StudentClassListScreen(
                    viewModel = vm,
                    onLogoutClick = { navController.navigate("welcomeScreen") { popUpTo("studentClassListScreen") { inclusive = true } } },
                    onAddClassClick = { navController.navigate("studentAddClassScreen") },
                    onClassListElementClick = { classId ->
                        navController.navigate("studentClassDetailScreen/$classId")
                    }
                )
            }

            /* ───── STUDENT – SZCZEGÓŁ KLASY ───── */
            composable(
                route = "studentClassDetailScreen/{classId}",
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                val vm: StudentClassDetailVm = hiltViewModel(backStackEntry)
                val classId = backStackEntry.arguments?.getString("classId") ?: ""
                StudentClassDetailScreen(
                    viewModel = vm,
                    onBackToClassListClick  = { navController.navigate("studentClassListScreen") },
                    onIUnderstandClick      = { /* opcjonalnie */ },
                    onIDontUnderstandClick  = { /* opcjonalnie */ },
                    onGoToQuestionFromTeacher = { navController.navigate("studentReceivedQuestionScreen/$classId") }
                )
            }

            /* ───── STUDENT – PYTANIE OD NAUCZYCIELA ───── */
            composable(
                route = "studentReceivedQuestionScreen/{classId}",
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                StudentReceivedQuestionScreen(
                    viewModel = viewModel,
                    onAnswerAClick        = { /* TODO */ },
                    onAnswerBClick        = { /* TODO */ },
                    onAnswerCClick        = { /* TODO */ },
                    onBackToClassDetailClick = {
                        val classId = backStackEntry.arguments?.getString("classId") ?: ""
                        navController.navigate("studentClassDetailScreen/$classId")
                    }
                )
            }

            /* ───── STUDENT – DODAWANIE KLASY ───── */
            composable("studentAddClassScreen") {
                val vm: StudentAddClassVm = hiltViewModel()
                StudentAddClassScreen(
                    viewModel = vm,
                    onBackToClassListScreen = { navController.navigate("studentClassListScreen") }
                )
            }
        }
    }
}
