package com.example.domain.usecase

import com.example.domain.model.PokemonFlavorText
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonFlavorTextUsecase @Inject constructor(
    private val repository: PokemonRepository
){
    suspend operator fun invoke(id:Int): Flow<PokemonFlavorText> {
        return repository.getPokemonFlavorText(id)
    }
}