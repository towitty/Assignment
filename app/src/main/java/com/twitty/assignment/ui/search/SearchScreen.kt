package com.twitty.assignment.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.twitty.assignment.ui.components.ExpandableBookItem
import com.twitty.assignment.ui.components.SearchBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val books by viewModel.books.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            SearchBar(
                onSearch = viewModel::searchBooks,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                items(items = books, key = { it.isbn }) { book ->
                    ExpandableBookItem(
                        book = book,
                        onClickIcon = viewModel::toggleFavoriteBook
                    )
                }
            }
        }
    }
}