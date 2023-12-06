package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    @Json(name = "flavor_text") val flavorText: String,
    @Json(name = "language") val language: CommonForm
)
