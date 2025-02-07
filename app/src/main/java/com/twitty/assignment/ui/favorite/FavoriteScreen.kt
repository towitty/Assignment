package com.twitty.assignment.ui.favorite

import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favoriteBooks by viewModel.favoriteBooks.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        ) {
            items(items = favoriteBooks, key = { it.isbn }) { book ->
                ExpandableBookItem(
                    book = book,
                    onClickIcon = viewModel::toggleFavoriteBook
                )
            }
        }
    }
}