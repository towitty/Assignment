package com.twitty.assignment.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twitty.assignment.data.repository.BookRepository
import com.twitty.assignment.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _books: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val favoriteBooks: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())

    init {
        fetchFavoriteBooks()
    }

    private fun fetchFavoriteBooks() {
        viewModelScope.launch {

            bookRepository.fetchFavoriteBooks().collect { newFavoriteBooks ->

                if (books.first().isNotEmpty()) {
                    val removedFavoriteBook = (favoriteBooks.first() - newFavoriteBooks).firstOrNull()

                    _books.update { oldBooks ->
                        oldBooks.map { oldBook ->
                            removedFavoriteBook?.let {
                                if (oldBook.isbn == removedFavoriteBook.isbn) {
                                    oldBook.copy(isFavorites = false)
                                } else {
                                    oldBook
                                }
                            } ?: oldBook
                        }
                    }
                }

                favoriteBooks.update { newFavoriteBooks }
            }
        }
    }

    fun searchBooks(title: String) {
        viewModelScope.launch {
            val searchedBooks = bookRepository.searchBooks(title)

            if (favoriteBooks.first().isEmpty()) {
                _books.update { searchedBooks }
            } else {
                _books.update {
                    searchedBooks.map { book ->
                        if (favoriteBooks.first().any { favoriteBook -> favoriteBook.isbn == book.isbn }) {
                            book.copy(isFavorites = true)
                        } else {
                            book
                        }
                    }
                }
            }
        }
    }

    fun toggleFavoriteBook(book: Book) {
        viewModelScope.launch {
            bookRepository.toggleFavoriteBook(book)

            _books.update { oldBooks ->
                oldBooks.map { oldBook ->
                    if (oldBook.isbn == book.isbn) {
                        oldBook.copy(isFavorites = !oldBook.isFavorites)
                    } else {
                        oldBook
                    }
                }
            }
        }
    }

}