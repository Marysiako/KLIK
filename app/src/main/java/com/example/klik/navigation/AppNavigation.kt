import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.klik.ui.screens.LoginScreen
import com.example.klik.ui.screens.RegisterScreen
import com.example.klik.ui.screens.WelcomeScreen
import com.example.klik.ui.screens.student.StudentAddClassScreen
import com.example.klik.ui.screens.student.StudentClassDetailScreen
import com.example.klik.ui.screens.student.StudentClassListScreen
import com.example.klik.ui.screens.student.StudentReceivedQuestionScreen
import com.example.klik.ui.screens.teacher.TeacherAskQuestionResultScreen
import com.example.klik.ui.screens.teacher.TeacherAskQuestionScreen
import com.example.klik.ui.screens.teacher.TeacherClassListScreen
import com.example.klik.ui.screens.teacher.TeacherCreateClassScreen
import com.example.klik.ui.screens.teacher.TeacherReceivedQuestionsScreen
import com.example.klik.viewmodel.AuthViewModel
import com.example.klik.viewmodel.student.StudentAddClassVm
import com.example.klik.viewmodel.student.StudentClassListVm
import com.example.klik.viewmodel.teacher.TeacherClassDetailVm
import com.example.klik.viewmodel.teacher.TeacherClassListVm
import com.example.klik.viewmodel.teacher.TeacherCreateClassVm

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
            //EKRANY  ---------------------------------------------------------------
            composable("welcomeScreen") {
                WelcomeScreen(
                    viewModel = viewModel,
                    onLoginClick = { navController.navigate("loginScreen") },
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
                            else          -> {/* fallback */}
                        }
                    },
                    bypassLoginStudentClick = { navController.navigate("studentClassListScreen") },
                    bypassLoginTeacherClick = { navController.navigate("teacherClassListScreen")})
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
            //EKRANY TEACHER    -----------------------------------------------------
            composable("teacherClassListScreen"){backStackEntry ->
                val vm: TeacherClassListVm = hiltViewModel(backStackEntry)
                TeacherClassListScreen(
                    viewModel = vm,
                    onLogoutClick         = { navController.navigate("welcomeScreen") { popUpTo("teacherClassList") { inclusive = true } } },
                    onCreateClassClick    = { navController.navigate("teacherCreateClassScreen") },
                    onClassListElementClick = { classId ->
                        navController.navigate("teacherClassDetailScreen/$classId")
                    }
                )
            }
            composable("teacherClassDetailScreen"){backStackEntry ->
                val vm: TeacherClassDetailVm = hiltViewModel(backStackEntry)
                TeacherClassDetailScreen(
                    viewModel = vm,
                    onBackToClassListClick = {navController.navigate("teacherClassListScreen")},
                    onAskStudentsClick     = { navController.navigate("teacherAskQuestionScreen/${backStackEntry.arguments?.getString("classId")}") },
                    onReceivedQuestionClick= { navController.navigate("teacherReceivedQuestionsScreen/${backStackEntry.arguments?.getString("classId")}") }
                    )
            }
            composable("teacherCreateClassScreen"){backStackEntry ->
                val vm: TeacherCreateClassVm = hiltViewModel(backStackEntry)
                TeacherCreateClassScreen(
                    viewModel = vm,
                    onBackToClassListClick = { navController.popBackStack()}
                )
            }
            composable("teacherAskQuestionScreen"){
                TeacherAskQuestionScreen(
                    viewModel = viewModel,
                    onBackToClassClick = {navController.navigate("teacherClassListScreen")},
                    onSendToStudentsClick = {/* ZAIMPLEMENTOWAC PROSZE*/}
                )
            }
            composable("teacherAskQuestionResultScreen"){
                TeacherAskQuestionResultScreen(
                    viewModel = viewModel,
                    onCBackToClassClick = {navController.navigate("teacherClassDetailScreen")}
                    )
            }
            composable("teacherReceivedQuestionsScreen"){
                TeacherReceivedQuestionsScreen(
                    viewModel = viewModel,
                    onBackToClassClick = {navController.navigate("teacherClassListScreen")}
                )
            }
            //EKRANY STUDENT --------------------------------------------------------
            composable("studentClassListScreen"){
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
            composable("studentClassDetailScreen"){
                StudentClassDetailScreen(
                    viewModel = viewModel,
                    onBackToClassListClick = {navController.navigate("studentClassListScreen")},
                    onIUnderstandClick = {/* TODO: ZAIMPLEMENTOWAC */},
                    onIDontUnderstandClick = {/* TODO: ZAIMPLEMENTOWAC */},
                    onSendQuestionToTeacherClick = {/* TODO: ZAIMPLEMENTOWAC */}
                )
            }
            composable("studentReceivedQuestionScreen"){
                StudentReceivedQuestionScreen(
                    viewModel = viewModel,
                    onAnswerAClick = {/* TODO: ZAIMPLEMENTOWAC */},
                    onAnswerBClick = {/* TODO: ZAIMPLEMENTOWAC */},
                    onAnswerCClick = {/* TODO: ZAIMPLEMENTOWAC */},
                    onBackToClassDetailClick = {navController.navigate("studentClassListScreen")}
                )
            }
            composable("studentAddClassScreen"){
                val vm: StudentAddClassVm = hiltViewModel()
                StudentAddClassScreen(
                    viewModel = vm,
                   // onAddClassClick = {navController.navigate("studentClassListScreen")}, /* TODO: ZAIMPLEMENTOWAC */
                    onBackToClassListScreen = {navController.navigate("studentClassListScreen")}
                )
            }

        }
    }
}