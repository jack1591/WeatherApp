package com.example.weatherapp.api

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    //val day: Day,
    val hour: List<Hour>
)

data class Hour(
    val time: String,
    val temp_c: String,
    val wind_kph: String,
    //val precip_mm: String,
    /*
    val feelslike_c: String,
    val heatindex_c: String,
    val windchill_c: String,
    val humidity: String,
    val cloud: String,
    val uv: String,
     */
    val condition: Condition
)

data class Day(
    //val maxtemp_c: Double,
    //val mintemp_c: Double,
    val avgtemp_c: Double,
    val condition: Condition
)