package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "language") val language:CommonForm
)
