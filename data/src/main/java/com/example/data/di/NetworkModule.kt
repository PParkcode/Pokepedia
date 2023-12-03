package com.example.data.di

import com.example.data.Api
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providePokeApiService(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}
