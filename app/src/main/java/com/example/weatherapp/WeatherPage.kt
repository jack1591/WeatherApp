package com.example.weatherapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.api.NetworkResponse
import com.example.weatherapp.api.WeatherModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun WeatherPage(viewModel: WeatherViewModel){
    var city by remember {
        mutableStateOf("")
    }

    val weatherResult = viewModel.weatherResult.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = city,
                onValueChange = {
                    city = it
                },
                label = {
                    Text(text = "search")
                })
            IconButton(onClick = {
                viewModel.getData(city)
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search_location")
            }
        }
        when(val result = weatherResult.value){
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }
            is NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                WeatherDetails(data = result.data, viewModel)
            }
            null -> {}
        }
    }
}

@Composable
fun WeatherDetails(data: WeatherModel, viewModel:WeatherViewModel){
    var list = arrayListOf(1,2,3)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ){
            Icon(imageVector = Icons.Default.LocationOn,
                contentDescription = "LocationIcon",
                modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = data.location.name, fontSize = 30.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = data.location.country, fontSize = 18.sp,color = Color.Gray)

        }
        Spacer(modifier = Modifier.height(25.dp))


        LazyRow {
            item{
                Button(shape = RectangleShape,
                    onClick = {
                        viewModel.currentState.value = 0
                    }) {
                    Text(text = "сейчас")
                }
                Spacer(modifier = Modifier.width(5.dp))
            }

            items(list){ elem->
                PeriodButton(number = elem,
                    onClick = {
                        CoroutineScope(Dispatchers.Default).launch {
                            viewModel.weatherInfoList.clear()
                            viewModel.currentState.value = elem
                            if (elem == 1) {
                                for (i in 0..23 step 2) {
                                    val hourInfo = data.forecast.forecastday[0].hour[i]
                                    val periodInfoData = PeriodInfoData(
                                        hourInfo.time,
                                        hourInfo.condition.icon,
                                        hourInfo.temp_c,
                                        hourInfo.wind_kph
                                    )
                                    viewModel.weatherInfoList.add(periodInfoData)
                                }
                            } else if (elem == 2) {
                                for (i in 0..1) {
                                    val dayInfo = data.forecast.forecastday[i]
                                    for (j in 0..23 step 3) {
                                        val hourInfo = dayInfo.hour[j]
                                        val periodInfoData = PeriodInfoData(
                                            hourInfo.time,
                                            hourInfo.condition.icon,
                                            hourInfo.temp_c,
                                            hourInfo.wind_kph
                                        )
                                        viewModel.weatherInfoList.add(periodInfoData)
                                    }
                                }
                            } else if (elem == 3) {
                                for (i in 0..2) {
                                    val dayInfo = data.forecast.forecastday[i]
                                    for (j in 0..23 step 6) {
                                        val hourInfo = dayInfo.hour[j]
                                        val periodInfoData = PeriodInfoData(
                                            hourInfo.time,
                                            hourInfo.condition.icon,
                                            hourInfo.temp_c,
                                            hourInfo.wind_kph
                                        )
                                        viewModel.weatherInfoList.add(periodInfoData)
                                    }
                                }
                            }
                        }
                    })


                Spacer(modifier = Modifier.width(5.dp))
            }

        }

        Spacer(modifier = Modifier.height(25.dp))
        if (viewModel.currentState.value == 0)
            CurrentWeather(data)
        else PeriodInfoList(list = viewModel.weatherInfoList)

    }
}