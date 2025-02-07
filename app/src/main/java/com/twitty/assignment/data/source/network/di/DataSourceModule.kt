package com.twitty.assignment.data.source.network.di

import com.twitty.assignment.data.source.network.retrofit.NetworkDataSource
import com.twitty.assignment.data.source.network.retrofit.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsNetworkDataSource(networkDataSource: NetworkDataSourceImpl): NetworkDataSource
}