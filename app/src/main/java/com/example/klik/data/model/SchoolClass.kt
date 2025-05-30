package com.example.klik.data.model

import androidx.annotation.Keep

@Keep
data class SchoolClass(
    val id: String = "",                   // = documentId w Firestore
    val name: String = "",
    val teacherId: String = "",
    val studentIds: List<String> = emptyList()
)
