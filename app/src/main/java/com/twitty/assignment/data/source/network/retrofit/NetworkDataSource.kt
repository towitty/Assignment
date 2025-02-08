package com.twitty.assignment.data.source.network.retrofit

import androidx.paging.PagingData
import com.twitty.assignment.data.source.network.model.NetworkBook
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {
    suspend fun searchBooks(query: String): Flow<PagingData<NetworkBook>>
}