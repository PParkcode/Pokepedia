package com.example.presentation.common

import androidx.lifecycle.ViewModel
import com.example.domain.model.PokemonCover
import com.example.domain.usecase.GetKoreanNameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(

    private val getKoreanNameUsecase: GetKoreanNameUsecase

): ViewModel() {
    private var _pokemon = MutableStateFlow<PokemonCover>(PokemonCover(0,""))
    var pokemon = _pokemon.asStateFlow()

    suspend fun getKoreanName(id:Int) {
        getKoreanNameUsecase.invoke(id).collect {
            _pokemon.value.name = it
        }
    }
    suspend fun getKoreanName() {
        getKoreanNameUsecase.invoke(pokemon.value.id).collect {
            _pokemon.value.name = it
        }
    }

    fun changePokemon(pokemonCover: PokemonCover) {
        _pokemon.value = pokemonCover
    }
}