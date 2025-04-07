package com.example.klik

import AppNavigation
import KLIKViewModel
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
import com.example.klik.ui.screens.teacher.TeacherClassListScreen

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