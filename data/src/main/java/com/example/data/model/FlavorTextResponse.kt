package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlavorTextResponse(
    @Json(name = "flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>
)
