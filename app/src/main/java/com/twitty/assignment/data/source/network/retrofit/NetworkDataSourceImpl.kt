package com.twitty.assignment.data.source.network.retrofit

import com.twitty.assignment.data.source.network.model.NetworkBookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val bookApi: ApiService
) : NetworkDataSource {

    override suspend fun searchBooks(query: String): Result<NetworkBookResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response: Response<NetworkBookResponse> = bookApi.searchBooks(query)

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        Result.success(body)
                    } ?: Result.failure(IOException("Response body is null"))
                } else {
                    Result.failure(IOException("API request failed code: ${response.code()}"))
                }
            } catch (e: IOException) {
                Result.failure(IOException("Network error: ${e.message}"))
            } catch (e: Exception) {
                Result.failure(Exception("An unexpected error occurred: ${e.message}"))
            }
        }
}