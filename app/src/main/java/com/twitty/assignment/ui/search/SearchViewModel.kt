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
                updateBooksWithFavorites()
            }
        }
    }

    private fun updateBooksWithFavorites() {
        _books.update { oldBooks ->
            oldBooks.map { oldBook ->
                favoriteBooks.value.find { it.isbn == oldBook.isbn }?.let { favoriteBook ->
                    oldBook.copy(isFavorites = true)
                } ?: oldBook.copy(isFavorites = false)
            }
        }
    }

    fun searchBooks(title: String) {
        viewModelScope.launch {
            bookRepository.searchBooks(title).cachedIn(this).collect { pagingData ->
                _books.emit(pagingData)
            }
        }
    }

    fun toggleFavoriteBook(book: Book) {
        viewModelScope.launch {
            bookRepository.toggleFavoriteBook(book)
        }
    }

}