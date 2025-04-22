package com.example.weatherapp

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape

@Composable
fun PeriodButton(number: Int, onClick: () -> Unit){
    Button(shape = RectangleShape,
        onClick = {
            onClick()
        }) {
        Text(if (number == 1) "1 день" else if (number<5) "$number дня" else "$number дней")
    }
}