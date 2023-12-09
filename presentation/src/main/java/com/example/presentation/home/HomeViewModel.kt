package com.example.presentation.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.model.PokemonCover
import com.example.domain.usecase.GetKoreanNameUsecase
import com.example.domain.usecase.GetPokemonsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonsUsecase: GetPokemonsUsecase,
    private val getKoreanNameUsecase: GetKoreanNameUsecase
) : ViewModel() {

    private var offset by mutableIntStateOf(0)
    private var limit by mutableIntStateOf(60)

    private var _pokemons = MutableStateFlow<List<PokemonCover>>(emptyList())
    var pokemons = _pokemons.asStateFlow()

    suspend fun getPokemons() {
        getPokemonsUsecase(offset,limit).collect {

            _pokemons.value += it.pokemons
            offset = it.nextOffset
            limit = it.nextLimit
        }
    }

    suspend fun getKoreanName(id: Int):String {
        getKoreanNameUsecase.invoke(id).collect {
            _pokemons.value[id - 1].name = it
        }
        return _pokemons.value[id -1 ].name
    }

}