package com.example.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.CurrentDayWeatherStatus
import com.example.weatherapp.ui.theme.DarkBackgroundColor70
import com.example.weatherapp.ui.theme.DarkBorderColor
import com.example.weatherapp.ui.theme.DarkTextColor60
import com.example.weatherapp.ui.theme.DarkTextColor87
import com.example.weatherapp.ui.theme.LightBackgroundColor70
import com.example.weatherapp.ui.theme.LightBorderColor
import com.example.weatherapp.ui.theme.LightTextColor60
import com.example.weatherapp.ui.theme.LightTextColor87
import com.example.weatherapp.ui.theme.Urbanist

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun WeatherStatusGrid(isDay: Boolean, todayWeatherStatus: CurrentDayWeatherStatus?) {
    FlowRow(
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        maxItemsInEachRow = 3
    ) {
        val gridItemModifier = Modifier.weight(1f)
        GridItem(
            icon = painterResource(R.drawable.fast_wind),
            value = "${todayWeatherStatus?.windSpeed ?: "-"} KM/h",
            type = "Wind",
            isDay = isDay,
            modifier = gridItemModifier
        )
        GridItem(
            icon = painterResource(R.drawable.humidity),
            value = "${todayWeatherStatus?.humidity ?: "-"}%",
            type = "Humidity",
            isDay = isDay,
            modifier = gridItemModifier
        )
        GridItem(
            icon = painterResource(R.drawable.rain),
            value = "${todayWeatherStatus?.rain ?: "-"}%",
            type = "Rain",
            isDay = isDay,
            modifier = gridItemModifier
        )
        GridItem(
            icon = painterResource(R.drawable.uv),
            value = "${todayWeatherStatus?.uvIndex ?: "-"}",
            type = "UV Index",
            isDay = isDay,
            modifier = gridItemModifier
        )
        GridItem(
            icon = painterResource(R.drawable.pressure),
            value = "${todayWeatherStatus?.pressure ?: "-"} hPa",
            type = "Pressure",
            isDay = isDay,
            modifier = gridItemModifier
        )
        GridItem(
            icon = painterResource(R.drawable.temperature),
            value = "${todayWeatherStatus?.feelsLike ?: "-"}°C",
            type = "Feels like",
            isDay = isDay,
            modifier = gridItemModifier
        )
    }
}

@Composable
private fun GridItem(
    icon: Painter,
    value: String,
    type: String,
    isDay: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 1.dp,
                color = if (isDay) LightBorderColor else DarkBorderColor,
                shape = RoundedCornerShape(24.dp)
            )
            .background(color = if (isDay) LightBackgroundColor70 else DarkBackgroundColor70)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
            )
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = value,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.25.sp,
                    color = if (isDay) LightTextColor87 else DarkTextColor87,
                )
                Text(
                    text = type,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 17.sp,
                    letterSpacing = 0.25.sp,
                    color = if (isDay) LightTextColor60 else DarkTextColor60,
                    modifier = Modifier.padding(top = 2.dp),
                )
            }
        }
    }
}
