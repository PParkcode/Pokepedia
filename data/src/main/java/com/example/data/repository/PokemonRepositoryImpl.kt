package com.example.data.repository

import android.util.Log
import com.example.data.datasource.remote.PokemonRemoteDataSource
import com.example.data.mapper.getId
import com.example.data.mapper.toCover
import com.example.data.model.PokemonResponse
import com.example.domain.model.PokemonCover
import com.example.domain.model.PokemonList
import com.example.domain.repository.PokemonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun getPokemons(offset:Int, limit:Int): PokemonList {
        val pokemonResponse = remoteDataSource.getPokemons(offset,limit)

        return toPokemonList(pokemonResponse)
    }

    suspend fun getKoreanName(id:Int):String {
        val speciesResponse = remoteDataSource.getKoreanName(id)
        return speciesResponse.names[2].name
    }

    private suspend fun toPokemonList(pokemonResponse: PokemonResponse): PokemonList {
        val pokemonList: List<PokemonCover> = pokemonResponse.results.map { it ->
            val id = getId(it)
            val koreanName = getKoreanName(id)
            /*
            toCover(
                pokemonResult = it
            )
             */
            toCover(id,koreanName)
        }
        var offset = 0
        var limit = 0

        var query = pokemonResponse.next?.split("?")?.last()
        query?.split("&")?.map { it ->
            val parameter = it.split("=")
            if (parameter[0] == "offset") {
                offset = parameter[1].toInt()
            } else {
                limit = parameter[1].toInt()
            }
        }
        Log.d("확인","offset: ${offset}")
        Log.d("확인","pokemons: $pokemonList")
        Log.d("확인","size: ${pokemonList.size}")


        return PokemonList(offset, limit, pokemonList)
    }
}