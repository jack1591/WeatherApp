package com.example.weatherapp20.search.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SearchLocationBar(
    state: LocationState,
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel
) {
    val name = remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                }
            )
            IconButton(
                onClick = {
                    Log.i("change_name1", state.locationName)
                    viewModel.changeName(name.value)
                    Log.i("change_name2", state.locationName)
                    viewModel.loadCoordinates()
                },
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator() // Показываем индикатор загрузки
                } else {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        }

        // Отображаем координаты, если они есть
        state.locationInfo?.let { data ->
            Row(
                modifier = modifier.padding(16.dp)
            ) {
                Text(text = "latitude = ${data.latitude} and longitude = ${data.longitude}")
            }
        }

        // Логируем координаты, когда они обновляются
        LaunchedEffect(state.locationInfo) {
            state.locationInfo?.let { data ->
                Log.i("change_name3", "Latitude: ${data.latitude}, Longitude: ${data.longitude}")
            }
        }
    }
}

/*
@Composable
fun SearchLocationBar(
    state: LocationState,
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel
){
    val name = remember{mutableStateOf("")}

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                }
            )
            IconButton(onClick = {
                Log.i("change_name1", state.locationName)
                viewModel.changeName(name.value)
                Log.i("change_name2", state.locationName)
                viewModel.loadCoordinates()
                //Log.i("change_name3", state.locationInfo?.longitude.toString())
            },
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator() // Показываем индикатор загрузки
                } else {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }

            }
        }
        state.locationInfo?.let { data ->
            Row(
                modifier = modifier.padding(16.dp)
            ) {
                Text(text = "latitude = ${data.latitude} and longitude = ${data.longitude}")
            }
        }
    }
}

 */