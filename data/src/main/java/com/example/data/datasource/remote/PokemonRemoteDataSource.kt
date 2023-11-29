package com.example.data.datasource.remote

import com.example.data.model.PokemonResponse

interface PokemonRemoteDataSource {

    suspend fun getPokemons(offset:Int, limit:Int): PokemonResponse
}