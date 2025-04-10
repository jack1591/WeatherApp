package com.example.weatherapp20.weather.di

import com.example.weatherapp20.weather.data.location.DefaultLocationTracker
import com.example.weatherapp20.weather.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {


    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTracker) : LocationTracker
}