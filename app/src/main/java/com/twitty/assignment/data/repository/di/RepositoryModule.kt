package com.twitty.assignment.data.repository.di

import com.twitty.assignment.data.repository.BookRepository
import com.twitty.assignment.data.repository.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBookRepository(repository: BookRepositoryImpl): BookRepository

}