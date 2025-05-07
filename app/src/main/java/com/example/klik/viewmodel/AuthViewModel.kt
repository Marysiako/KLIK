/**
 *  AuthViewModel.kt
 *  – logowanie i rejestracja użytkownika
 *  – po udanej rejestracji tworzy dokument /students lub /teachers
 *
 *  Uwaga: login traktujemy jak „username”, dlatego tworzymy sztuczny e-mail
 *      username@klik.app  →  FirebaseAuth działa tylko na e-mailach.
 */
package com.example.klik.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klik.data.model.Student
import com.example.klik.data.model.Teacher
import com.example.klik.data.remote.StudentRemoteDs
import com.example.klik.data.remote.TeacherRemoteDs
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val studentRemote: StudentRemoteDs,
    private val teacherRemote: TeacherRemoteDs
) : ViewModel() {

    /** stany dla UI */
    var isLoading = mutableStateOf(false); private set
    var errorMsg = mutableStateOf<String?>(null); private set
    var isLoggedIn = mutableStateOf(false); private set
    var role = mutableStateOf<String?>(null); private set   // "Uczeń" lub "Nauczyciel"

    /** zamiana „username” → „username@klik.app” (Auth wymaga e-maila) */
    private fun toEmail(username: String) = "$username@klik.app"

    //LOGIN

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            errorMsg.value = "Wpisz login i hasło"
            return
        }

        viewModelScope.launch {
            isLoading.value = true
            errorMsg.value  = null
            try {
                auth.signInWithEmailAndPassword(toEmail(username), password).await()
                val uid = auth.currentUser!!.uid

                val student = studentRemote.get(uid)
                val teacher = teacherRemote.get(uid)

                role.value       = student?.role ?: teacher?.role
                isLoggedIn.value = role.value != null

            } catch (e: Exception) {
                errorMsg.value = e.message
            } finally {
                isLoading.value = false
            }
        }

    }

    //REGISTER

    fun register(username: String, password: String, selectedRole: String) {
        if (username.isBlank() || password.isBlank()) {
            errorMsg.value = "Wpisz nazwę i hasło"
            return
        }

        viewModelScope.launch {
            isLoading.value = true
            errorMsg.value = null
            try {
                // 1) tworzymy użytkownika w FirebaseAuth
                val result =
                    auth.createUserWithEmailAndPassword(toEmail(username), password).await()
                val uid = result.user!!.uid

                // 2) dodajemy dokument w Firestore
                if (selectedRole == "Uczeń") {
                    val student = Student(uid = uid, username = username, role = "Uczeń")
                    studentRemote.create(student)
                } else {   // "Nauczyciel"
                    val teacher = Teacher(uid = uid, username = username, role = "Nauczyciel")
                    teacherRemote.create(teacher)
                }

                isLoggedIn.value = true
                role.value = selectedRole
            } catch (e: Exception) {
                errorMsg.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }
}
    /* ───── pomocnicze ──────────────────────────────────────── */
/*
    /** sprawdza, czy UID istnieje w kolekcji students, w przeciwnym razie teacher */
    private suspend fun detectRole(uid: String): String =
    val stud = StudentRemoteDs.get(uid)
    if (stud != null) return stud.role

    val teach = teacherRemote.get(uid)
    if (teach != null) return teach.role

    return null

}
*/