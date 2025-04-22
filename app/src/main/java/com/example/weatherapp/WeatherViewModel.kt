package com.example.weatherapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.Constant
import com.example.weatherapp.api.NetworkResponse
import com.example.weatherapp.api.RetrofitInstance
import com.example.weatherapp.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult
    val currentState = mutableStateOf(-1)
    val weatherInfoList = mutableListOf<PeriodInfoData>()

    fun getData(city: String){
        _weatherResult.value = NetworkResponse.Loading
        currentState.value = 0
        viewModelScope.launch {
            try{
                val response = weatherApi.getWeather(Constant.apiKey, city, 14)
                if (response.isSuccessful){
                    response.body()?.let{
                        _weatherResult.value = NetworkResponse.Success(it)
                        Log.d("api result", _weatherResult.value.toString())
                    }
                }
                else {
                    _weatherResult.value = NetworkResponse.Error("Failure to load data(")
                }
            }
            catch (e:Exception){
                _weatherResult.value = NetworkResponse.Error("Failure to load data(")
            }
        }
    }
}