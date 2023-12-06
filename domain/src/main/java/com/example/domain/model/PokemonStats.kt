package com.example.domain.model

data class PokemonStats(
    var height: String = "0",
    var weight: String = "0",
    var hp: Int = 0,
    var atk: Int = 0,
    var def: Int = 0,
    var specialAtk: Int = 0,
    var specialDef: Int = 0,
    var speed: Int = 0
)
