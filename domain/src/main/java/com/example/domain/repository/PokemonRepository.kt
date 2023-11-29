package com.example.domain.repository

import com.example.domain.model.PokemonList

interface PokemonRepository {

    suspend fun getPokemons(offset:Int, limit:Int):PokemonList
}