package com.example.data.datasource.remote

import android.util.Log
import com.example.data.Api
import com.example.data.model.PokemonResponse
import com.example.data.model.SpeciesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private var client: Api
) : PokemonRemoteDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): Flow<PokemonResponse> = flow {
        Log.d("확인", "getPokemons at dataSource")
        emit(client.getPokemonList(offset, limit))
    }

    override suspend fun getKoreanName(id: Int): Flow<SpeciesResponse> = flow {
         emit(client.getKoreanName(id))
    }
}
