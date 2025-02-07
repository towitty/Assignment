package com.twitty.assignment.data.source.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val price: String,
    val publisher: String,
    val publicationDate: String,
    val isbn: String,
    val description: String,
    val isFavorites: Boolean = false,
)