package com.example.domain.usecase

import android.util.Log
import com.example.domain.model.PokemonList
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsUsecase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(offset:Int,limit:Int): Flow<PokemonList> {
        Log.d("확인","유스케이스")
        return repository.getPokemons(offset, limit)
    }
}