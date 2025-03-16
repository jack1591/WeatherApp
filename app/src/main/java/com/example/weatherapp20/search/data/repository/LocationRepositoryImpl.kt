package com.example.weatherapp20.search.data.repository

import com.example.weatherapp20.search.data.remote.LocationApi
import com.example.weatherapp20.search.data.mappers.toLocationInfo
import com.example.weatherapp20.search.domain.location.LocationInfo
import com.example.weatherapp20.search.domain.repository.LocationRepository
import com.example.weatherapp20.weather.domain.util.Resource
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: LocationApi
):LocationRepository {
    override suspend fun getCoordinates(name: String): Resource<LocationInfo> {
        return try{
            Resource.Success(
                data = api.getCoordinates(
                    name = name
                ).toLocationInfo()
            )
        } catch(e: Exception){
            e.printStackTrace()
            Resource.Error(e.message?: "Unknown error(")
        }
    }
}