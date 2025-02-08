package com.twitty.assignment.data.repository

import androidx.paging.PagingData
import androidx.paging.map
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

    override suspend fun searchBooks(query: String): Flow<PagingData<Book>> = networkDataSource.searchBooks(query)
        .map { pagingData ->
            pagingData.map { networkBook ->
                networkBook.asBook()
            }
        }

    override suspend fun fetchFavoriteBooks(): Flow<List<Book>> = bookDao.fetchFavoriteBooks()
        .map { entities ->
            entities.map { entity ->
                entity.asBook()
            }
        }

    override suspend fun toggleFavoriteBook(book: Book) {
        if (book.isFavorites) {
            removeFavoriteBook(book)
        } else {
            addFavoriteBook(book)
        }
    }

    override suspend fun removeFavoriteBook(book: Book) {
        bookDao.fetchBookByIsbn(book.isbn).firstOrNull()?.let { bookEntity ->
            bookDao.deleteBook(bookEntity)
        }
    }

    override suspend fun addFavoriteBook(book: Book) {
        bookDao.insertBook(book.asEntity(true))
    }
}