package com.example.domain.repository

import com.example.domain.model.PokemonList
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemons(offset: Int, limit: Int): Flow<PokemonList>
    suspend fun getKoreanName(id: Int): Flow<String>
}