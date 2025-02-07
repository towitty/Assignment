package com.twitty.assignment.data.source.database.di

import android.content.Context
import androidx.room.Room
import com.twitty.assignment.data.source.database.AssignmentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAssignmentDatabase(
        @ApplicationContext context: Context
    ): AssignmentDatabase = Room
        .databaseBuilder(
            context,
            AssignmentDatabase::class.java,
            "assignment_db"
        )
        .build()

}