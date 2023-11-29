package com.example.domain.usecase

import com.example.domain.model.PokemonList
import com.example.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonsUsecase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(offset:Int,limit:Int): PokemonList {
        return repository.getPokemons(offset, limit)
    }
}