package com.twitty.assignment.data.repository

import com.twitty.assignment.data.source.network.model.asBook
import com.twitty.assignment.data.source.network.retrofit.NetworkDataSource
import com.twitty.assignment.model.Book
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : BookRepository {

    override suspend fun searchBooks(query: String): List<Book> {
        val networkBookResponse = networkDataSource.searchBooks(query)
        return networkBookResponse.books.map { it.asBook() }
    }

    override suspend fun fetchFavoriteBooks(): List<Book> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavoriteBook(book: Book) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavoriteBook(book: Book) {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteBook(book: Book) {
        TODO("Not yet implemented")
    }
}