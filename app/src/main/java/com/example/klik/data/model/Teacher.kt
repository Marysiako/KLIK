package com.example.klik.data.model

import androidx.annotation.Keep

@Keep
data class Teacher(
    val uid: String = "",                       // = FirebaseAuth.uid
    val username: String = "",
    val classIds: List<String> = emptyList(),
    val role: String = "Nauczyciel"
)