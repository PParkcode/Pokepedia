package com.example.domain.model

data class PokemonList(
    var nextOffset:Int,
    var nextLimit:Int,
    var pokemons:List<PokemonCover>
)
