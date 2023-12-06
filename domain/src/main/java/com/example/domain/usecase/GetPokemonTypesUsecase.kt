package com.example.domain.usecase

import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonTypesUsecase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Flow<List<String>> {
        return repository.getPokemonTypes(id)
    }
}