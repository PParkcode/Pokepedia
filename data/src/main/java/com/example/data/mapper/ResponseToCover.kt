package com.example.data.mapper

import com.example.data.model.PokemonResult
import com.example.domain.model.PokemonCover

inline fun toCover(pokemonResult:PokemonResult):PokemonCover {
    val id = pokemonResult.url.split("/".toRegex()).dropLast(1).last().toInt()
    return PokemonCover(id, pokemonResult.name)
}