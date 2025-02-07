package com.twitty.assignment.data.repository

import com.twitty.assignment.data.source.database.dao.BookDao
import com.twitty.assignment.data.source.database.model.asBook
import com.twitty.assignment.data.source.network.model.asBook
import com.twitty.assignment.data.source.network.retrofit.NetworkDataSource
import com.twitty.assignment.model.Book
import com.twitty.assignment.model.asEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val networkDataSource: NetworkDataSource
) : BookRepository {

    override suspend fun searchBooks(query: String): List<Book> {
        val networkBookResponse = networkDataSource.searchBooks(query)
        return networkBookResponse.books.map { it.asBook() }
    }

    override suspend fun fetchFavoriteBooks(): Flow<List<Book>> = bookDao.fetchFavoriteBooks()
        .map { entities ->
            entities.map { entity ->
                entity.asBook()
            }
        }

    override suspend fun toggleFavoriteBook(book: Book) {
        val books = bookDao.fetchBookByIsbn(book.isbn)

        books.firstOrNull()?.let { bookEntity ->
            bookDao.updateBook(bookEntity.toggleFavorites())
        } ?: bookDao.insertBook(book.asEntity(true))
    }

    override suspend fun removeFavoriteBook(book: Book) {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteBook(book: Book) {
        TODO("Not yet implemented")
    }
}