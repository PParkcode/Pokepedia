package com.example.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.model.PokemonCover
import com.example.domain.usecase.GetPokemonsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonsUsecase: GetPokemonsUsecase
) : ViewModel() {

    var offset by mutableStateOf(0)
    var limit by mutableStateOf(20)
    var pokemons by mutableStateOf<List<PokemonCover>>(emptyList())

    suspend fun getPokemons() {
        val pokemonResponse = getPokemonsUsecase.invoke(offset, limit)
        pokemons += pokemonResponse.pokemons.toMutableList()
        offset = pokemonResponse.nextOffset
        limit = pokemonResponse.nextLimit
    }

}