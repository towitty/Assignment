package com.twitty.assignment.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.twitty.assignment.common.PAGE_SIZE
import com.twitty.assignment.ui.components.ExpandableBookItem
import com.twitty.assignment.ui.components.SearchBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val books = viewModel.books.collectAsLazyPagingItems()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            SearchBar(
                onSearch = viewModel::searchBooks,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = books,
                    key = { book -> book.isbn }
                ) { book ->
                    book?.let {
                        ExpandableBookItem(
                            book = it,
                            onClickIcon = { viewModel.toggleFavoriteBook(it) }
                        )
                    }

                    val bookIndex = books.itemSnapshotList.indexOf(book) + 1
                    if (bookIndex % PAGE_SIZE == 0 && bookIndex > 9) {
                        Text(
                            text = "${bookIndex / PAGE_SIZE}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }

                if (books.itemCount % PAGE_SIZE != 0) {
                    item {
                        Text(
                            text = "${(books.itemCount / PAGE_SIZE) + 1}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}