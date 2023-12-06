package com.example.data.mapper

import com.example.data.model.PokemonResponse
import com.example.data.model.PokemonResult
import com.example.domain.model.PokemonCover
import com.example.domain.model.PokemonList


inline fun toCover(id: Int, name: String): PokemonCover {
    return PokemonCover(id, name)
}

inline fun getId(pokemonResult: PokemonResult): Int {
    return pokemonResult.url.split("/".toRegex()).dropLast(1).last().toInt()
}

fun toPokemonList(pokemonResponse: PokemonResponse): PokemonList {
    val pokemonList: List<PokemonCover> = pokemonResponse.results.map { it ->
        val id = getId(it)
        toCover(id, it.name)
    }

    val offsetAndLimit = getOffsetAndLimit(pokemonResponse.next?.split("?")?.last())

    return PokemonList(offsetAndLimit.first, offsetAndLimit.second, pokemonList)
}

fun getOffsetAndLimit(query: String?): Pair<Int, Int> {
    var offset = 0
    var limit = 0
    query?.split("&")?.map { it ->
        val parameter = it.split("=")
        if (parameter[0] == "offset") {
            offset = parameter[1].toInt()
        } else {
            limit = parameter[1].toInt()
        }
    }
    return Pair(offset, limit)
}