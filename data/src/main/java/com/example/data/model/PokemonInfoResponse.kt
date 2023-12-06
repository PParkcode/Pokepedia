package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfoResponse(
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "weight")
    val weight: Int,
    @Json(name = "stats")
    val stats: List<Stats>,
    @field:Json(name = "types")
    val types: List<Types>
)

@JsonClass(generateAdapter = true)
data class Stats(
    @Json(name = "base_stat")
    val baseStat: Int,
    @Json(name = "stat")
    val stat: CommonForm
)

@JsonClass(generateAdapter = true)
data class Types(
    @field:Json(name = "slot")
    val slot: Int,
    @field:Json(name = "type")
    val type: CommonForm
)

