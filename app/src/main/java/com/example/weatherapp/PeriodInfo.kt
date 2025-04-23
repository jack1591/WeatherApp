package com.example.weatherapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.api.WeatherModel

data class PeriodInfoData(
    var periodOfTime: String,
    val nameOfImage: String,
    val temp_c: String,
    val windSpeed: String
)

@Composable
fun PeriodInfo(periodInfoData: PeriodInfoData){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = periodInfoData.periodOfTime.substring(
                    periodInfoData.periodOfTime.indexOf(" ") + 1,
                    periodInfoData.periodOfTime.length
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            AsyncImage(
                model = "https:${periodInfoData.nameOfImage}".replace("64x64", "128x128"),
                contentDescription = "conditionIcon",
                modifier = Modifier
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "${periodInfoData.temp_c} °c")
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "${periodInfoData.windSpeed} km/h")
        }

}