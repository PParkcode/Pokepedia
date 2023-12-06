package com.example.data

import com.example.data.model.FlavorTextResponse
import com.example.data.model.PokemonInfoResponse
import com.example.data.model.PokemonResponse
import com.example.data.model.NamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonResponse

    @GET("pokemon-species/{id}")
    suspend fun getKoreanName(
        @Path("id") id: Int
    ): NamesResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(
        @Path("id") id: Int
    ): PokemonInfoResponse

    @GET("pokemon-species/{id}")
    suspend fun getFlavorText(
        @Path("id") id: Int
    ): FlavorTextResponse

    @GET
    suspend fun getType(@Url url:String): NamesResponse
}
