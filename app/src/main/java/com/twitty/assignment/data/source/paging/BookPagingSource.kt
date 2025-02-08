package com.twitty.assignment.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.twitty.assignment.common.PAGE_SIZE
import com.twitty.assignment.data.source.network.model.NetworkBook
import com.twitty.assignment.data.source.network.model.NetworkBookResponse
import com.twitty.assignment.data.source.network.retrofit.ApiService
import retrofit2.Response
import javax.inject.Inject

class BookPagingSource @Inject constructor(
    private val bookApi: ApiService,
    private val query: String,
) : PagingSource<Int, NetworkBook>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkBook> {
        val page = params.key ?: 0
        return try {
            val response: Response<NetworkBookResponse> =
                bookApi.searchBooks(query = query, display = PAGE_SIZE, start = (PAGE_SIZE * page) + 1)

            if (response.isSuccessful) {
                val books = response.body()?.books ?: emptyList()
                val total = response.body()?.total ?: 0
                val prevKey = if (page == 0) null else page - 1
                val nextKey = if ((PAGE_SIZE * page) + PAGE_SIZE >= total) null else page + 1

                LoadResult.Page(
                    data = books,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception("Error code: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NetworkBook>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}