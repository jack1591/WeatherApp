package com.example.weatherapp20.search.presentation

import com.example.weatherapp20.search.domain.location.LocationInfo

data class LocationState(
    val locationName: String = "",
    val locationInfo: LocationInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)