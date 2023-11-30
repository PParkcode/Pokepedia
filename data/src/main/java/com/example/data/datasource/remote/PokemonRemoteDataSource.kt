package com.example.data.datasource.remote

import com.example.data.model.PokemonResponse
import com.example.data.model.SpeciesResponse

interface PokemonRemoteDataSource {

    suspend fun getPokemons(offset:Int, limit:Int): PokemonResponse

    suspend fun getKoreanName(id:Int): SpeciesResponse
}