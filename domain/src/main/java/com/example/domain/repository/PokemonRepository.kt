package com.example.domain.repository

import com.example.domain.model.PokemonFlavorText
import com.example.domain.model.PokemonList
import com.example.domain.model.PokemonStats
import com.example.domain.model.PokemonType
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemons(offset: Int, limit: Int): Flow<PokemonList>

    suspend fun getKoreanName(id: Int): Flow<String>

    suspend fun getPokemonStats(id: Int):Flow<PokemonStats>

    suspend fun getPokemonTypes(id:Int):Flow<List<PokemonType>>

    suspend fun getPokemonFlavorText(id: Int): Flow<PokemonFlavorText>
}