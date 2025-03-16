package com.example.weatherapp20.search.data.remote

import com.squareup.moshi.Json

data class LocationDataDto(

    @field:Json(name = "latitude")
    val latitude : Double,

    @field:Json(name = "longitude")
    val longitude : Double
)