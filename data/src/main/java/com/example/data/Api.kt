package com.example.data

import com.example.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ):PokemonResponse
}