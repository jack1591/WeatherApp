package com.example.weatherapp20.search.data.mappers

import com.example.weatherapp20.search.data.remote.LocationDto
import com.example.weatherapp20.search.domain.location.LocationInfo

fun LocationDto.toLocationInfo(): LocationInfo {
    val location = locationData
    return LocationInfo(
        latitude = location.latitude,
        longitude = location.longitude
    )
}