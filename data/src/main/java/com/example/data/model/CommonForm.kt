package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommonForm(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)
