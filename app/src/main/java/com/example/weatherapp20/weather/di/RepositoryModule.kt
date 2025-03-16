package com.example.weatherapp20.weather.di

import com.example.weatherapp20.data.location.DefaultLocationTracker
import com.example.weatherapp20.data.repository.WeatherRepositoryImpl
import com.example.weatherapp20.domain.location.LocationTracker
import com.example.weatherapp20.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ) : WeatherRepository
}