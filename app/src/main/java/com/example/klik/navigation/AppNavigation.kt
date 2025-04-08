import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.ui.Modifier
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

@Composable
fun AppNavigation(viewModel: KLIKViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(
        // bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "teacherClassListScreen",
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
                LoginScreen(viewModel = viewModel)
            }
            composable("registerScreen") {
                RegisterScreen(viewModel = viewModel)
            }
            //EKRANY TEACHER    -----------------------------------------------------
            composable("teacherClassListScreen"){
                TeacherClassListScreen(
                    viewModel = viewModel,
                    onLogoutClick = {navController.navigate("welcomeScreen")},
                    onCreateClassClick = {navController.navigate("teacherCreateClassScreen")},
                    onClassListElementClick = {/* TODO: ZAIMPLEMENTOWAC */}
                )
            }
            composable("teacherClassDetailScreen"){
                TeacherClassDetailScreen(
                    viewModel = viewModel,
                    onBackToClassListClick = {navController.navigate("teacherClassListScreen")},
                    onAskStudentsClick = {navController.navigate("teacherAskQuestionScreen")},
                    onReceivedQuestionClick = {navController.navigate("teacherReceivedQuestionsScreen")}
                    )
            }
            composable("teacherCreateClassScreen"){
                TeacherCreateClassScreen(
                    viewModel = viewModel,
                    onCreateClassClick = { /* ZAIMPLEMENTOWAC PROSZE*/ },
                    onBackToClassListClick = {navController.navigate("teacherClassListSceen")}
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
                StudentClassListScreen(
                    viewModel = viewModel,
                    onLogoutClick = {navController.navigate("welcomeScreen")},
                    onAddClassClick = {navController.navigate("studentAddClassScreen")},
                    onClassListElementClick = {/* TODO: ZAIMPLEMENTOWAC */}
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
                StudentAddClassScreen(
                    viewModel = viewModel,
                    onAddClassClick = {/* TODO: ZAIMPLEMENTOWAC */},
                    onBackToClassListScreen = {/* TODO: ZAIMPLEMENTOWAC */}
                )
            }

        }
    }
}