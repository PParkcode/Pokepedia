package com.example.data.mapper

import com.example.data.model.PokemonResult
import com.example.domain.model.PokemonCover

inline fun toCover(pokemonResult: PokemonResult): PokemonCover {
    val id = pokemonResult.url.split("/".toRegex()).dropLast(1).last().toInt()
    return PokemonCover(id, pokemonResult.name)
}

inline fun toCover(id: Int, name: String): PokemonCover {
    return PokemonCover(id, name)
}

inline fun getId(pokemonResult: PokemonResult): Int {
    return pokemonResult.url.split("/".toRegex()).dropLast(1).last().toInt()
}
