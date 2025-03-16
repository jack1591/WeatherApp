package com.example.weatherapp20.search.data.remote

import com.squareup.moshi.Json

data class LocationDto(
    @field:Json(name = "results")
    val locationData: LocationDataDto
)