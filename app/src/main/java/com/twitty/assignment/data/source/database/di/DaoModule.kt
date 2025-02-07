package com.twitty.assignment.data.source.database.di

import com.twitty.assignment.data.source.database.AssignmentDatabase
import com.twitty.assignment.data.source.database.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun bindsNetworkDataSource(appDatabase: AssignmentDatabase): BookDao = appDatabase.bookDao()

}