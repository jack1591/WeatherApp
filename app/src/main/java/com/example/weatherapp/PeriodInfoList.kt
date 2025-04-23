package com.example.weatherapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PeriodInfoList(numberOfDays: Int, list: MutableList<PeriodInfoData>){
    val months = arrayOf(
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря",
        )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0..<list.size/4){

            if (i==0) {
                val dateStr = list[i * 4].periodOfTime.substring(
                    0,
                    list[i * 4].periodOfTime.indexOf(" ")
                )
                val info = dateStr.split('-').toTypedArray()

                Spacer(Modifier.height(20.dp))
                Text(
                    text = info[2]+" "+months[info[1].toInt()-1]+" "+info[0]
                )
                Spacer(Modifier.height(10.dp))
            }
            else {
                val str1 = list[i*4].periodOfTime.substring(0,list[i*4].periodOfTime.indexOf(" "))
                val str0 = list[i*4-4].periodOfTime.substring(0,list[i*4-4].periodOfTime.indexOf(" "))
                if (str1!=str0) {
                    val info = str1.split('-').toTypedArray()
                    Spacer(Modifier.height(20.dp))
                    Text(text = info[2]+" "+months[info[1].toInt()-1]+" "+info[0])
                    Spacer(Modifier.height(10.dp))
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                val dayTimeList = arrayOf("Ночь","Утро","День","Вечер")

                for (j in 0..3){
                    if (numberOfDays == 3)
                        PeriodInfo(periodInfoData = PeriodInfoData(dayTimeList[j],list[i*4+j].nameOfImage, list[i*4+j].temp_c, list[i*4+j].windSpeed))
                    else PeriodInfo(periodInfoData = list[i*4+j])
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}