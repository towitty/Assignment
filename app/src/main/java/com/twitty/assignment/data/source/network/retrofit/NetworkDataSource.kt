package com.twitty.assignment.data.source.network.retrofit

import com.twitty.assignment.data.source.network.model.NetworkBookResponse

interface NetworkDataSource {
    suspend fun searchBooks(query: String): NetworkBookResponse
}