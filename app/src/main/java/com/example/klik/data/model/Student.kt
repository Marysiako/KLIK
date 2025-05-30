package com.example.klik.data.model

import androidx.annotation.Keep

@Keep
data class Student(
    val uid: String = "",                  // = FirebaseAuth.uid
    val username: String = "",
    val classIds: List<String> = emptyList(),
    val role: String = "Uczeń"
)
