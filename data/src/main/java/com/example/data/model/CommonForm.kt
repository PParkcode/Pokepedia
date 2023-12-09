package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * PokeApi에서 자주 사용되는 형식이라 CommonForm으로 재사용
 */
@JsonClass(generateAdapter = true)
data class CommonForm(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)
