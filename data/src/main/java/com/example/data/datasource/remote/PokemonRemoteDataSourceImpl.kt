package com.example.data.datasource.remote

import com.example.data.Api
import com.example.data.model.PokemonResponse
import com.example.data.model.SpeciesResponse
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private var client: Api
) : PokemonRemoteDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): PokemonResponse {
        return client.getPokemonList(offset, limit)
    }

    override suspend fun getKoreanName(id: Int): SpeciesResponse {
        return client.getKoreanName(id)
    }
}