package com.example.weatherapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.api.WeatherModel

@Composable
fun CurrentWeather(data: WeatherModel){
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ){
        Text(text = "${data.current.temp_c} °c",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "${data.current.feelslike_c} °c",
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(20.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = "https:${data.current.condition.icon}".replace("64x64","128x128"),
            contentDescription = "consitionIcon",
            modifier = Modifier
                .size(150.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = data.current.condition.text,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(modifier = Modifier.height(15.dp))

    Card {
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                WeatherKeyValue("Local time", data.location.localtime.substring(data.location.localtime.indexOf(" ")+1))
                WeatherKeyValue("Local date", data.location.localtime.substring(0,data.location.localtime.indexOf(" ")))
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                WeatherKeyValue("Precipitation", data.current.precip_mm)
                WeatherKeyValue("Wind speed", data.current.wind_kph+" km/h")
            }
        }
    }
}


@Composable
fun WeatherKeyValue(key: String, value: String){
    Column (
        modifier = Modifier
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = value, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text(text = key,fontSize = 15.sp, fontWeight = FontWeight.SemiBold,color = Color.Gray)

    }
}
