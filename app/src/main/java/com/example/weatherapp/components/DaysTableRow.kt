package com.example.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.DarkTextColor
import com.example.weatherapp.ui.theme.DarkTextColor60
import com.example.weatherapp.ui.theme.DarkTextColor87
import com.example.weatherapp.ui.theme.LightTextColor
import com.example.weatherapp.ui.theme.LightTextColor60
import com.example.weatherapp.ui.theme.LightTextColor87
import com.example.weatherapp.ui.theme.Urbanist

@Composable
fun DaysTableRow(
    day: String,
    resource: Painter,
    maxTemp: Int,
    minTemp: Int,
    isDay: Boolean,
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DayText(day, isDay)
        Image(
            painter = resource,
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 6.5.dp)
                .height(32.dp)
                .weight(1f),
            contentScale = ContentScale.FillHeight
        )
        RangeTemperaturesByDay(
            maxTemperature = maxTemp, minTemperature = minTemp,
            isDay,
            modifier = Modifier.weight(1.5f)
        )
    }
}

@Preview
@Composable
private fun DaysTableRowPreview() {
    Box(
        Modifier.background(color = Color.White)
    ) {
        DaysTableRow(
            "Monday",
            painterResource(R.drawable.day_partly_cloudy),
            32,
            20,
            true
        )
    }
}

@Composable
private fun RowScope.DayText(
    day: String,
    isDay: Boolean
) {
    Text(
        text = day,
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.sp,
        letterSpacing = 0.25.sp,
        color = if (isDay) LightTextColor60 else DarkTextColor60,
        modifier = Modifier
            .padding(top = 4.dp)
            .weight(1.5f)
    )
}

@Composable
private fun RangeTemperaturesByDay(
    maxTemperature: Int,
    minTemperature: Int,
    isDay: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MinMaxTemperatures(maxTemperature, minTemperature, isDay)
    }
}

@Composable
private fun MinMaxTemperatures(maxDegree: Int, minDegree: Int, isDay: Boolean) {
    DegreeMinMax14(maxDegree, painterResource(R.drawable.arrow_up), isDay)
    VerticalDivider(
        thickness = 1.dp,
        color = if (isDay) Color.Black.copy(alpha = 0.24f) else Color.White.copy(alpha = 0.24f),
        modifier = Modifier
            .height(14.dp)
            .padding(horizontal = 4.dp)
    )
    DegreeMinMax14(minDegree, painterResource(R.drawable.arrow_down), isDay)
}

@Composable
private fun DegreeMinMax14(degree: Int, iconResource: Painter, isDay: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = iconResource,
            contentDescription = null,
            tint = if (isDay) LightTextColor else DarkTextColor,
            modifier = Modifier.size(12.dp),
        )
        Text(
            text = "$degree°C",
            fontFamily = Urbanist,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 17.sp,
            letterSpacing = 0.25.sp,
            color = if (isDay) LightTextColor87 else DarkTextColor87,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}
