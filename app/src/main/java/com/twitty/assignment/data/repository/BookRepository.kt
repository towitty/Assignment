package com.twitty.assignment.data.repository

import androidx.paging.PagingData
import com.twitty.assignment.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Flow<PagingData<Book>>
    suspend fun fetchFavoriteBooks(): Flow<List<Book>>
    suspend fun toggleFavoriteBook(book: Book)
    suspend fun removeFavoriteBook(book: Book)
    suspend fun addFavoriteBook(book: Book)
}