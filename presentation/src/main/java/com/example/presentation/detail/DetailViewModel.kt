package com.example.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.domain.model.PokemonCover
import com.example.domain.model.PokemonFlavorText
import com.example.domain.model.PokemonStats
import com.example.domain.model.Stat
import com.example.domain.usecase.GetPokemonFlavorTextUsecase
import com.example.domain.usecase.GetPokemonStatsUsecase
import com.example.domain.usecase.GetPokemonTypesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonStatsUsecase: GetPokemonStatsUsecase,
    private val getPokemonFlavorTextUsecase: GetPokemonFlavorTextUsecase,
    private val getPokemonTypesUsecase: GetPokemonTypesUsecase

) : ViewModel() {
    private var _pokemonStats = MutableStateFlow<PokemonStats>(
        PokemonStats(
            "0",
            "0",
            Stat("", 0, 0),
            Stat("", 0, 0),
            Stat("", 0, 0),
            Stat("", 0, 0),
            Stat("", 0, 0),
            Stat("", 0, 0)
        )
    )
    var pokemonStats = _pokemonStats.asStateFlow()

    private var _pokemonTypes = MutableStateFlow<List<String>>(emptyList())
    var pokemonTypes = _pokemonTypes.asStateFlow()

    private var _pokemonFlavorText = MutableStateFlow(PokemonFlavorText(""))
    var pokemonFlavorText = _pokemonFlavorText.asStateFlow()

    private var _pokemon = MutableStateFlow<PokemonCover>(PokemonCover(0, ""))
    var pokemon = _pokemon.asStateFlow()


    suspend fun getPokemonStats(id: Int) {
        getPokemonStatsUsecase(id).collect {
            _pokemonStats.value = it
        }
    }

    suspend fun getPokemonTypes(id: Int) {
        getPokemonTypesUsecase(id).collect {
            _pokemonTypes.value = it
        }
    }

    suspend fun getPokemonFlavorText(id: Int) {
        getPokemonFlavorTextUsecase(id).collect {
            _pokemonFlavorText.value = it
        }

    }

    fun setPokemonNameId(id: Int, name: String) {
        _pokemon.value = PokemonCover(id, name)
    }
}