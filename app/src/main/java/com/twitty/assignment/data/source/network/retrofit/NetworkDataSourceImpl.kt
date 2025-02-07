package com.twitty.assignment.data.source.network.retrofit

import android.util.Log
import com.twitty.assignment.data.source.network.model.NetworkBookResponse
import com.twitty.assignment.data.source.network.model.emptyNetworkBookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val bookApi: ApiService
) : NetworkDataSource {

    override suspend fun searchBooks(query: String): NetworkBookResponse {
        return withContext(Dispatchers.IO) {
            try {
                val response = bookApi.searchBooks(query)
                if (response.isSuccessful) {
                    response.body() ?: emptyNetworkBookResponse
                } else {
                    Log.e("Network", "fail code: ${response.code()}")
                    emptyNetworkBookResponse
                }
            } catch (e: Exception) {
                Log.e("Network","NetworkException: ${e.message}")
                emptyNetworkBookResponse
            }
        }
    }
}