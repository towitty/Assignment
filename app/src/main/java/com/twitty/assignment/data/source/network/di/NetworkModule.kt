package com.twitty.assignment.data.source.network.di

import com.twitty.assignment.data.source.network.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providesRetrofit(
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/v1/search/")
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun providesApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)
}