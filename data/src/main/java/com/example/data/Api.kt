package com.example.data

import com.example.data.model.PokemonResponse
import com.example.data.model.SpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): PokemonResponse

    @GET("pokemon-species/{id}")
    suspend fun getKoreanName(
        @Path("id") id: Int
    ): SpeciesResponse
}
