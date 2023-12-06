package com.example.data.datasource.remote

import com.example.data.model.FlavorTextResponse
import com.example.data.model.PokemonInfoResponse
import com.example.data.model.PokemonResponse
import com.example.data.model.NamesResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {

    suspend fun getPokemons(offset: Int, limit: Int): Flow<PokemonResponse>

    suspend fun getKoreanName(id: Int): Flow<NamesResponse>

    suspend fun getPokemonInfo(id:Int): Flow<PokemonInfoResponse>

    suspend fun getPokemonFlavorText(id:Int): Flow<FlavorTextResponse>

    suspend fun getPokemonType(url:String): Flow<NamesResponse>
}
