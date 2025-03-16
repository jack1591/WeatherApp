package com.example.weatherapp20.weather.domain.repository

import com.example.weatherapp20.weather.domain.util.Resource
import com.example.weatherapp20.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}