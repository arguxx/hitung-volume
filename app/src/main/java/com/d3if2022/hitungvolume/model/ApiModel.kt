package com.d3if2022.hitungvolume.model

import com.squareup.moshi.Json

data class ApiModel(
    @field:Json(name = "name") val name: String = "",
    @field:Json(name = "company") val company: String = "",
    @field:Json(name = "location") val location: String = "",
    @field:Json(name = "avatar_url") val avatar_url: String = "",
)