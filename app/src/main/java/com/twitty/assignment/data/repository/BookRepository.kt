package com.twitty.assignment.data.repository

import com.twitty.assignment.model.Book

interface BookRepository {
    suspend fun searchBooks(query: String): List<Book>
    suspend fun fetchFavoriteBooks(): List<Book>
    suspend fun toggleFavoriteBook(book: Book)
    suspend fun removeFavoriteBook(book: Book)
    suspend fun addFavoriteBook(book: Book)
}