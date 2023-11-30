package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LanguageForm(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url:String
)
