package com.twitty.assignment.data.source.network.retrofit

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.twitty.assignment.data.source.network.model.NetworkBook
import com.twitty.assignment.data.source.paging.BookPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val bookApi: ApiService
) : NetworkDataSource {

    override suspend fun searchBooks(query: String): Flow<PagingData<NetworkBook>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BookPagingSource(bookApi, query) }
        ).flow
    }
}