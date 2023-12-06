package com.example.data.datasource.remote

import android.util.Log
import com.example.data.Api
import com.example.data.model.FlavorTextResponse
import com.example.data.model.PokemonInfoResponse
import com.example.data.model.PokemonResponse
import com.example.data.model.NamesResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private var client: Api
) : PokemonRemoteDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): Flow<PokemonResponse> = flow {
        emit(client.getPokemonList(offset, limit))
    }

    override suspend fun getKoreanName(id: Int): Flow<NamesResponse> = flow {
         emit(client.getKoreanName(id))
    }

    override suspend fun getPokemonInfo(id: Int): Flow<PokemonInfoResponse> = flow {
        emit(client.getPokemonInfo(id))
    }

    override suspend fun getPokemonFlavorText(id: Int):Flow<FlavorTextResponse> = flow {
        emit(client.getFlavorText(id))
    }

    override suspend fun getPokemonType(url:String):Flow<NamesResponse> = flow {
        val a= client.getType(url)
        Log.d("확인","type 응답" + a.names)
        emit(a)
    }
}
