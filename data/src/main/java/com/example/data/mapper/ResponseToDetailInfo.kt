package com.example.data.mapper

import com.example.data.model.FlavorTextEntry
import com.example.data.model.PokemonInfoResponse
import com.example.domain.model.PokemonFlavorText
import com.example.domain.model.PokemonStats
import com.example.domain.model.Stat



fun getPokemonStats(pokemonResponse: PokemonInfoResponse): PokemonStats {
    var hp = 0
    var attack = 0
    var defense = 0
    var specialAtk = 0
    var specialDef = 0
    var speed = 0

    for (item in pokemonResponse.stats) {
        when (item.stat.name) {
            "hp" -> {
                hp = item.baseStat
            }

            "attack" -> {
                attack = item.baseStat
            }

            "defense" -> {
                defense = item.baseStat
            }

            "special-attack" -> {
                specialAtk = item.baseStat
            }

            "special-defense" -> {
                specialDef = item.baseStat
            }

            "speed" -> {
                speed = item.baseStat
            }
        }
    }
    val height = pokemonResponse.height.toString()
    val weight = pokemonResponse.weight.toString()

    // 받아온 데이터를 소수점 형태로 변환
    val modifiedHeight = if (height.length == 1) {
        "0.$height"
    } else {
        StringBuilder(height).insert(height.length - 1, ".").toString()
    }

    val modifiedWeight = if (weight.length == 1) {
        "0.$weight"
    } else {
        StringBuilder(weight).insert(weight.length - 1, ".").toString()
    }


    return PokemonStats(
        height = modifiedHeight,
        weight = modifiedWeight,
        hp = Stat("HP", hp, 0xFFFF0000),
        atk = Stat("ATK", attack, 0xFFF08030),
        def = Stat("DEF", defense, 0xFFF8D030),
        specialAtk = Stat("SP-ATK", specialAtk, 0xFF6890F0),
        specialDef = Stat("SP-DEF", specialDef, 0xFF78C850),
        speed = Stat("SPD", speed, 0xFFF85888)
    )
}


inline fun toPokemonFlavorText(flavorTextEntry: FlavorTextEntry): PokemonFlavorText {
    return PokemonFlavorText(flavorTextEntry.flavorText)
}
