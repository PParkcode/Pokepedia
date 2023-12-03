package com.example.data.datasource.remote

import com.example.data.model.PokemonResponse
import com.example.data.model.SpeciesResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {

    suspend fun getPokemons(offset: Int, limit: Int): Flow<PokemonResponse>

    suspend fun getKoreanName(id: Int): Flow<SpeciesResponse>
}
