package com.example.klik.ui.screens.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.example.klik.data.model.SchoolClass
import com.example.klik.data.model.Student
import com.example.klik.data.repository.ClassRepository
import com.example.klik.data.repository.StudentRepository
import com.example.klik.viewmodel.student.StudentAddClassVm
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flowOf

@Composable
fun StudentAddClassScreen(
    viewModel: StudentAddClassVm,
    onBackToClassListScreen: () -> Unit) {

    // Pamiętane stany dla pól tekstowych
    var subjectID by remember { mutableStateOf("") }    //!!!BEDZIE TRZEBA ZMIENIC NA INT BO OutlinedTextField przyjmuje tylko string!!!!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Górny nagłówek
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Dodaj Klase",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Pola tekstowe
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Odpowiedzi A, B, C
            OutlinedTextField(
                value = subjectID,
                onValueChange = { subjectID = it },
                label = { Text("Numer ID") },
                modifier = Modifier.fillMaxWidth()
            )
        }


        // Przycisk dodajacy klase
        Button(
            onClick = {
                viewModel.onJoinClass(
                    classId = subjectID,
                    onSuccess = onBackToClassListScreen   // wracamy po sukcesie
                )
            },
            enabled = subjectID.isNotBlank() && !viewModel.isJoining,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            if (viewModel.isJoining) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("Dodaj")
            }
        }


        // Dolna nawigacja
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = onBackToClassListScreen,
                icon = {},
                label = { Text("Powrót do klas") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentAddClassScreenPreview() {
    val previewVm = remember {
        StudentAddClassVm(
            studentRepo = object : StudentRepository {
                override fun current() = flowOf(Student())
                override suspend fun addClass(classId: String) {}
                override fun uid(): String = "previewUid"
            },
            classRepo = object : ClassRepository {
                override fun observe(id: String)           = flowOf(SchoolClass())
                override fun observeMany(ids: List<String>) =
                    flowOf(emptyList<SchoolClass>())
                override suspend fun create(schoolClass: SchoolClass) {}
                override suspend fun addStudent(classId: String, studentId: String) {}
                override suspend fun get(id: String): SchoolClass? {
                    TODO("Not yet implemented")
                }
            },
            auth = FirebaseAuth.getInstance()
        )
    }

    // ▶︎ 2. Wywołujemy ekran z tym VM-em
    StudentAddClassScreen(
        viewModel = previewVm,
        onBackToClassListScreen = {}
    )
}
