package com.example.data.di

import com.example.data.datasource.remote.PokemonRemoteDataSource
import com.example.data.datasource.remote.PokemonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    abstract fun bindDataSource(
        dataSource: PokemonRemoteDataSourceImpl
    ): PokemonRemoteDataSource
}