package com.example.weatherapp20.weather.data.repository

import com.example.weatherapp20.data.mappers.toWeatherInfo
import com.example.weatherapp20.data.remote.WeatherApi
import com.example.weatherapp20.domain.repository.WeatherRepository
import com.example.weatherapp20.domain.util.Resource
import com.example.weatherapp20.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try{
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message?: "Unknown error(" )
        }
    }

}