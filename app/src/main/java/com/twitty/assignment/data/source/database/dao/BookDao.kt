package com.twitty.assignment.data.source.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.twitty.assignment.data.source.database.model.BookEntity

@Dao
interface BookDao {

    @Insert
    suspend fun insertBook(bookEntity: BookEntity): Long

    @Delete
    suspend fun deleteBook(bookEntity: BookEntity)

    @Update
    suspend fun updateBook(bookEntity: BookEntity)

    @Query("SELECT * FROM books WHERE id = :id LIMIT 1")
    suspend fun fetchBookById(id: Long): List<BookEntity>

    @Query("SELECT * FROM books WHERE isbn = :isbn LIMIT 1")
    suspend fun fetchBookByIsbn(isbn: String): List<BookEntity>

    @Query("SELECT * FROM books WHERE isFavorites = 1")
    fun fetchFavoriteBooks(): List<BookEntity>

}