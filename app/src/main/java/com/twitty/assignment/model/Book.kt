package com.twitty.assignment.model

import com.twitty.assignment.data.source.database.model.BookEntity

data class Book(
    val isbn: String = "0",
    val title: String,
    val link: String,
    val imageUrl: String,
    val isFavorites: Boolean = false
)

fun Book.asEntity(isFavorites: Boolean) = BookEntity(
    title = title,
    link = link,
    image = imageUrl,
    author = "",
    price = "",
    publisher = "",
    publicationDate = "",
    isbn = isbn,
    description = "",
    isFavorites = isFavorites
)
