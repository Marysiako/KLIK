package com.example.klik.data.model

import androidx.annotation.Keep

@Keep
data class Question(
    val id: String = "",                   // = documentId (subkolekcja)
    val text: String = "",
    val answers: List<String> = List(4) { "" },   // dokładnie 4 odpowiedzi
    val answerScores: List<Int> = List(4) { 0 },   // tablica wyników
    val fromTeacher: Boolean = false
)
