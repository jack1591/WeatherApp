package com.example.weatherapp20.search.di

import com.example.weatherapp20.search.data.remote.LocationApi
import com.example.weatherapp20.weather.data.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationSearchModule {

    @Provides
    @Singleton
    fun provideLocationSearchApi(): LocationApi {
        return Retrofit.Builder()
            .baseUrl("https://geocoding-api.open-meteo.com/")
            .addConverterFactory(
                MoshiConverterFactory.create()
            )
            .build()
            .create()
    }

}