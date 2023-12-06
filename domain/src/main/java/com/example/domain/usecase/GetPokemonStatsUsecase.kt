package com.example.domain.usecase

import com.example.domain.model.PokemonStats
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonStatsUsecase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(id:Int):Flow<PokemonStats> {
        return repository.getPokemonStats(id)
    }
}