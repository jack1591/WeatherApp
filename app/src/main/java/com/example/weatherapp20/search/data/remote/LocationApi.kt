package com.example.weatherapp20.search.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("/v1/search?count=1&language=en&format=json")
    suspend fun getCoordinates(
        @Query("name") name:String
    ): LocationDto
}