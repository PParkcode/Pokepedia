package com.example.data.repository

import android.util.Log
import com.example.data.datasource.remote.PokemonRemoteDataSource
import com.example.data.mapper.getId
import com.example.data.mapper.toCover
import com.example.data.model.PokemonResponse
import com.example.domain.model.PokemonCover
import com.example.domain.model.PokemonList
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun getPokemons(offset: Int, limit: Int): Flow<PokemonList> {
        Log.d("확인", "데이터소스")
        val pokemonResponse = remoteDataSource.getPokemons(offset, limit)
        val pokemonListFlow = pokemonResponse.map {
            Log.d("확인", "데이터 소스 $it")
            toPokemonList(it)
        }
        return pokemonListFlow

        // return toPokemonList(pokemonResponse)
    }

    override suspend fun getKoreanName(id: Int): Flow<String> {
        val koreanNameFlow = remoteDataSource.getKoreanName(id).map {
            it.names[2].name
        }
        return koreanNameFlow

    }

    private  fun toPokemonList(pokemonResponse: PokemonResponse): PokemonList {
        Log.d("확인", "toPokemonList 진입")
        val pokemonList: List<PokemonCover> = pokemonResponse.results.map { it ->
            val id = getId(it)
            toCover(id, it.name)
        }

        val offsetAndLimit = getOffsetAndLimit(pokemonResponse.next?.split("?")?.last())
        Log.d("확인", "$pokemonList")

        return PokemonList(offsetAndLimit.first, offsetAndLimit.second, pokemonList)
    }

    private fun getOffsetAndLimit(query: String?): Pair<Int, Int> {
        var offset = 0
        var limit = 0
        query?.split("&")?.map { it ->
            val parameter = it.split("=")
            if (parameter[0] == "offset") {
                offset = parameter[1].toInt()
            } else {
                limit = parameter[1].toInt()
            }
        }
        return Pair(offset, limit)
    }

}
