package com.example.weatherapp20.search.domain.repository

import com.example.weatherapp20.search.domain.location.LocationInfo
import com.example.weatherapp20.weather.domain.util.Resource

interface LocationRepository {
    suspend fun getCoordinates(name: String): Resource<LocationInfo>
}