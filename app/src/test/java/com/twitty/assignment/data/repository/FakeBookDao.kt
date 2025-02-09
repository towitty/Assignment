package com.twitty.assignment.data.repository

import com.twitty.assignment.data.source.database.dao.BookDao
import com.twitty.assignment.data.source.database.model.BookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeBookDao : BookDao {

    private val books = mutableListOf<BookEntity>()

    override suspend fun insertBook(bookEntity: BookEntity): Long {
        books.add(bookEntity)
        return 1
    }

    override suspend fun deleteBook(bookEntity: BookEntity) {
        books.remove(bookEntity)
    }

    override suspend fun updateBook(newBook: BookEntity) {
        books.find { it.id == newBook.id }?.let { oldBook ->
            books.remove(oldBook)
            books.add(newBook)
        }
    }

    override suspend fun fetchBookById(id: Long): List<BookEntity> =
        books.find { it.id == id }?.let { book ->
            listOf(book)
        } ?: emptyList()

    override suspend fun fetchBookByIsbn(isbn: String): List<BookEntity> =
        books.find { it.isbn == isbn }?.let { book ->
            listOf(book)
        } ?: emptyList()


    override fun fetchFavoriteBooks(): Flow<List<BookEntity>> =
        books.filter { it.isFavorites }.let { favoriteBooks ->
            return flowOf(favoriteBooks)
        }
}