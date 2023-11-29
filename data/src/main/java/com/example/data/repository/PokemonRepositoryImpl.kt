package com.example.data.repository

import com.example.data.datasource.remote.PokemonRemoteDataSource
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

    private fun toPokemonList(pokemonResponse: PokemonResponse): PokemonList {
        val pokemonList: List<PokemonCover> = pokemonResponse.results.map { it ->
            toCover(
                pokemonResult = it
            )
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


        return PokemonList(offset, limit, pokemonList)
    }
}