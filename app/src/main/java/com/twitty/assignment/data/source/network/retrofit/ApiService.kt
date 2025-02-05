package com.twitty.assignment.data.source.network.retrofit

import com.twitty.assignment.BuildConfig
import com.twitty.assignment.data.source.network.model.NetworkBookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    /**
     * 네이버 책 검색 API를 호출하여 검색 결과를 반환합니다.
     *
     * @param query 검색어. UTF-8로 인코딩 되어야 합니다. ISBN으로 검색 가능합니다.
     * @param display 한 번에 표시할 검색 결과 개수 (기본값: 10, 최댓값: 100).
     * @param start 검색 시작 위치 (기본값: 1, 최댓값: 100).
     * @param sort 검색 결과 정렬 방법 (기본값: sim 정확도 순으로 내림차순, 옵션: date 출간일 순으로 내림차순)
     *
     * @see <a href="https://developers.naver.com/docs/serviceapi/search/book/book.md#%EC%B1%85-%EA%
     * B2%80%EC%83%89-api-%EB%A0%88%ED%8D%BC%EB%9F%B0%EC%8A%A4">책 검색 Api 레퍼런스</a>
     */
    @GET("book.json")
    suspend fun searchBooks(
        @Query("query") query: String,
        @Header("X-Naver-Client-Id") clientId: String = BuildConfig.NAVER_API_CLIENT,
        @Header("X-Naver-Client-Secret") clientSecret: String = BuildConfig.NAVER_API_SECRET,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String = "sim",
    ): Response<NetworkBookResponse>
}