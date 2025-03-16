package com.example.weatherapp20.search.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp20.search.domain.repository.LocationRepository
import com.example.weatherapp20.weather.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository
): ViewModel() {
    var state by mutableStateOf(LocationState())
        private set

    fun changeName(name: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            state = state.copy(
                locationName = name,
                isLoading = false
            )
        }
    }

    fun loadCoordinates(){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when(val result = locationRepository.getCoordinates(state.locationName)){
                is Resource.Success -> {
                    state = state.copy(
                        locationInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error ->{
                    state = state.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}