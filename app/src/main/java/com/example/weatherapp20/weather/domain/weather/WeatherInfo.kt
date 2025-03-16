package com.example.weatherapp20.weather.domain.weather

import com.example.weatherapp20.data.remote.WeatherDto

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
