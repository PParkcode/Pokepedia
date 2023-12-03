package com.example.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.model.PokemonCover
import com.example.domain.usecase.GetKoreanNameUsecase
import com.example.domain.usecase.GetPokemonsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonsUsecase: GetPokemonsUsecase,
    private val getKoreanNameUsecase: GetKoreanNameUsecase
) : ViewModel() {

    var offset by mutableStateOf(0)
    var limit by mutableStateOf(60)

    private var _pokemons = MutableStateFlow<List<PokemonCover>>(emptyList())
    var pokemons = _pokemons.asStateFlow()

    suspend fun getPokemons() {
        getPokemonsUsecase.invoke(offset, limit).collect {

            _pokemons.value += it.pokemons
            offset = it.nextOffset
            limit = it.nextLimit
        }
    }

    suspend fun getKoreanName(id: Int):String {
        getKoreanNameUsecase.invoke(id).collect {
            Log.d("확인", it)
            _pokemons.value[id - 1].name = it

        }
        return _pokemons.value[id -1 ].name
    }

}