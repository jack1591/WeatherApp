package com.example.weatherapp20.domain.repository

import com.example.weatherapp20.domain.util.Resource
import com.example.weatherapp20.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}