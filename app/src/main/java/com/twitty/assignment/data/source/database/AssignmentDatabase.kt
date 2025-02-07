package com.twitty.assignment.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.twitty.assignment.data.source.database.dao.BookDao
import com.twitty.assignment.data.source.database.model.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class AssignmentDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

}