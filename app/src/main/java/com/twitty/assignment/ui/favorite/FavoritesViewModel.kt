package com.twitty.assignment.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twitty.assignment.data.repository.BookRepository
import com.twitty.assignment.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _favoriteBooks: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val favoriteBooks: StateFlow<List<Book>> = _favoriteBooks

    init {
        fetchFavoriteBooks()
    }

    private fun fetchFavoriteBooks() {
        viewModelScope.launch {
            bookRepository.fetchFavoriteBooks().collect { favoriteBooks ->
                _favoriteBooks.value = favoriteBooks
            }
        }
    }

    fun toggleFavoriteBook(book: Book) {
        viewModelScope.launch {
            bookRepository.toggleFavoriteBook(book)
        }
    }
}