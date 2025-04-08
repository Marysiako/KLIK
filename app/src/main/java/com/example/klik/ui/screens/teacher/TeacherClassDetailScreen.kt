import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.klik.ui.screens.teacher.TeacherClassListScreen

@Composable
fun TeacherClassDetailScreen(
    viewModel: KLIKViewModel,
    onBackToClassListClick: () -> Unit,
    onAskStudentsClick: () -> Unit,
    onReceivedQuestionClick: () -> Unit
) {
    // Przykładowe dane – zastąpić danymi z ViewModel
    val subjectName = "Matematyka"
    val classId = 101

    var understandCount by remember { mutableStateOf(12) }
    var dontUnderstandCount by remember { mutableStateOf(5) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Górny nagłówek
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = subjectName,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "ID: $classId",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Dwa prostokąty jeden pod drugim
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.Red)
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Nie rozumiem", color = Color.White,
                        modifier = Modifier.weight(1f))
                    Text(dontUnderstandCount.toString(), color = Color.White,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.Green)
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Rozumiem", color = Color.White,
                        modifier = Modifier.weight(1f))
                    Text(understandCount.toString(), color = Color.White,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End)
                }
            }
        }


        // Przycisk resetujący
        Button(
            onClick = {
                understandCount = 0
                dontUnderstandCount = 0
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text("Resetuj")
        }

        // Dolna nawigacja
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = onBackToClassListClick,
                icon = {},
                label = { Text("Powrót do klas") }
            )
            NavigationBarItem(
                selected = false,
                onClick = onAskStudentsClick,
                icon = {},
                label = { Text("Zadaj pytanie") }
            )
            NavigationBarItem(
                selected = false,
                onClick = onReceivedQuestionClick,
                icon = {},
                label = { Text("Pytania") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherClassDetailScreenPreview() {
    TeacherClassDetailScreen(
        viewModel = KLIKViewModel(),
        onBackToClassListClick = {},
        onAskStudentsClick = {},
        onReceivedQuestionClick = {})
}