package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NamesResponse(
    @field:Json(name = "names") val names: List<Name>
)
