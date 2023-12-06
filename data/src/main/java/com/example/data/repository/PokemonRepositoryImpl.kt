package com.example.data.repository

import android.util.Log
import com.example.data.datasource.remote.PokemonRemoteDataSource
import com.example.data.mapper.getId
import com.example.data.mapper.getPokemonStats
import com.example.data.mapper.toCover
import com.example.data.mapper.toPokemonFlavorText
import com.example.data.mapper.toPokemonList
import com.example.data.model.FlavorTextEntry
import com.example.data.model.PokemonInfoResponse
import com.example.data.model.PokemonResponse
import com.example.domain.model.PokemonCover
import com.example.domain.model.PokemonFlavorText
import com.example.domain.model.PokemonList
import com.example.domain.model.PokemonStats
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun getPokemons(offset: Int, limit: Int): Flow<PokemonList> {
        val pokemonResponse = remoteDataSource.getPokemons(offset, limit)
        val pokemonListFlow = pokemonResponse.map {
            toPokemonList(it)
        }
        return pokemonListFlow


    }

    override suspend fun getKoreanName(id: Int): Flow<String> {
        val koreanNameFlow = remoteDataSource.getKoreanName(id).map {
            it.names[2].name
        }
        return koreanNameFlow

    }

    override suspend fun getPokemonStats(id: Int): Flow<PokemonStats> {

        val pokemonInfo: Flow<PokemonInfoResponse> = remoteDataSource.getPokemonInfo(id)

        return pokemonInfo.map { getPokemonStats(it) }
    }

    override suspend fun getPokemonTypes(id: Int): Flow<List<String>> {
        val pokemonInfo: Flow<PokemonInfoResponse> = remoteDataSource.getPokemonInfo(id)
        val pokemonTypes = mutableListOf<String>()

        val pokemonTypesFlow = pokemonInfo.map { response ->
            val aaa = response.types.map {
                val a = remoteDataSource.getPokemonType(it.type.url).transform { nameResponse ->
                    var names = nameResponse.names.first { name ->
                        name.language.name == "ko"
                    }.name
                    emit(names)

                }
            }
        }
        return flowOf(listOf(" ", " ", " "))
    }

    override suspend fun getPokemonFlavorText(id: Int): Flow<PokemonFlavorText> {
        val pokemonFlavorText: Flow<FlavorTextEntry> =
            remoteDataSource.getPokemonFlavorText(id).map { response ->
                response.flavorTextEntries.first { entry ->
                    entry.language.name == "ko"
                }
            }
        return pokemonFlavorText.map { toPokemonFlavorText(it) }
    }

}

