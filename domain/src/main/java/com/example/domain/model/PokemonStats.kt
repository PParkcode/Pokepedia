package com.example.domain.model

data class PokemonStats(
    var height: String = "0",
    var weight: String = "0",
    var hp: Stat,
    var atk: Stat,
    var def: Stat,
    var specialAtk: Stat,
    var specialDef: Stat,
    var speed: Stat
)

data class Stat(
    var name:String,
    var value:Int,
    var color:Long
)
