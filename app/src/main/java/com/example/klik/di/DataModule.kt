package com.example.klik.di

import com.example.klik.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds abstract fun bindStudentRepo(impl: StudentRepositoryImpl): StudentRepository
    @Binds abstract fun bindTeacherRepo(impl: TeacherRepositoryImpl): TeacherRepository
    @Binds abstract fun bindClassRepo(impl: ClassRepositoryImpl): ClassRepository
    @Binds abstract fun bindQuestionRepo(impl: QuestionRepositoryImpl): QuestionRepository
}
