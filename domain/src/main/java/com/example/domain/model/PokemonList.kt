package com.example.domain.model

/**
 * 포켓몬 리스트를 받아오면,
 * 현재 리스트와 다음 오프셋 정보를 함께받아온다.
 */
data class PokemonList(
    var nextOffset:Int,
    var nextLimit:Int,
    var pokemons:List<PokemonCover>
)
