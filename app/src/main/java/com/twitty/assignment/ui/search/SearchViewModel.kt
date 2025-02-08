package com.twitty.assignment.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.twitty.assignment.data.repository.BookRepository
import com.twitty.assignment.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val emptyPagingData = PagingData.empty<Book>()

    private val _books: MutableStateFlow<PagingData<Book>> = MutableStateFlow(emptyPagingData)
    val books: StateFlow<PagingData<Book>> = _books

    private val favoriteBooks: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())

    init {
        fetchFavoriteBooks()
    }

    private fun fetchFavoriteBooks() {
        viewModelScope.launch {
            bookRepository.fetchFavoriteBooks().collect { newFavoriteBooks ->
                favoriteBooks.update { newFavoriteBooks }
                _books.update { oldBooks ->
                    oldBooks.map { oldBook ->
                        newFavoriteBooks.find { it.isbn == oldBook.isbn }?.let { favoriteBook ->
                            oldBook.copy(isFavorites = favoriteBook.isFavorites)
                        } ?: oldBook.copy(isFavorites = false)
                    }
                }
            }
        }
    }

    fun searchBooks(title: String) {
        viewModelScope.launch {
            val currentFavoriteBooks = favoriteBooks.value
            bookRepository.searchBooks(title).cachedIn(this).collect { pagingData ->
                val updatedPagingData = pagingData.map { book ->
                    if (currentFavoriteBooks.any { favoriteBook -> favoriteBook.isbn == book.isbn }) {
                        book.copy(isFavorites = true)
                    } else {
                        book
                    }
                }
                _books.emit(updatedPagingData)
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