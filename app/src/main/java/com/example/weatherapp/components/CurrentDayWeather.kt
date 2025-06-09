package com.example.weatherapp.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.DarkBorderColor
import com.example.weatherapp.ui.theme.DarkTextColor
import com.example.weatherapp.ui.theme.DarkTextColor60
import com.example.weatherapp.ui.theme.DarkTextColor87
import com.example.weatherapp.ui.theme.LightBorderColor
import com.example.weatherapp.ui.theme.LightTextColor
import com.example.weatherapp.ui.theme.LightTextColor60
import com.example.weatherapp.ui.theme.LightTextColor87
import com.example.weatherapp.ui.theme.Urbanist

@Composable
fun CurrentDayWeatherTest(
    scrollProgress: Float,
    isDay: Boolean,
    modifier: Modifier = Modifier
) {
    var isScrolled by remember { mutableStateOf(false) }
    isScrolled = scrollProgress > 0.5f
    var animatedTopPadding =
        animateDpAsState(if (isScrolled) 110.dp else 0.dp, animationSpec = tween(500))
    var animatedHeight =
        animateDpAsState(if (isScrolled) 160.dp else 375.dp, animationSpec = tween(500))
    val forecastAlignment by animateAlignmentAsState(if (isScrolled) Alignment.CenterStart else Alignment.TopCenter)
    val weatherAlignment by animateAlignmentAsState(if (isScrolled) Alignment.CenterEnd else Alignment.BottomCenter)

    Box(
        modifier = Modifier
            .padding(top = animatedTopPadding.value)
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = modifier
                .height(animatedHeight.value)
                .height(375.dp)
                .fillMaxWidth()
                .animateContentSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = forecastAlignment,
            ) {
                Box {
                    Image(
                        painter = painterResource(if (isDay) R.drawable.day_mainly_clear else R.drawable.night_mainly_clear),
                        contentDescription = "forecast",
                        modifier = Modifier.height((if (isScrolled) 112 else 200).dp),
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = weatherAlignment
            ) {
                Column(
                    modifier = Modifier.padding(top = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TemperatureDegree(24, isDay)
                    WeatherCondition(1, isDay)
                    DailyRangeTemperatures(32, 20, isDay)
                }
            }
        }
    }
}

@Composable
private fun DailyRangeTemperatures(
    maxDegree: Int,
    minDegree: Int,
    isDay: Boolean,
) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(color = if (isDay) LightBorderColor else DarkBorderColor)
            .padding(vertical = 8.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MinMaxTemperatures(maxDegree, minDegree, isDay)
    }
}

@Composable
private fun MinMaxTemperatures(maxDegree: Int, minDegree: Int, isDay: Boolean) {
    TemperatureMinMax(maxDegree, painterResource(R.drawable.arrow_up), isDay)
    VerticalDivider(
        thickness = 1.dp,
        color = if (isDay) Color.Black.copy(alpha = 0.24f) else Color.White.copy(alpha = 0.24f),
        modifier = Modifier
            .height(14.dp)
            .padding(horizontal = 8.dp)
    )
    TemperatureMinMax(minDegree, painterResource(R.drawable.arrow_down), isDay)
}

@Composable
private fun TemperatureMinMax(degree: Int, iconResource: Painter, isDay: Boolean) {
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
            fontSize = 16.sp,
            lineHeight = 19.sp,
            letterSpacing = 0.25.sp,
            color = if (isDay) LightTextColor87 else DarkTextColor87,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}

@Composable
private fun WeatherCondition(weatherStatus: Int, isDay: Boolean) {
    Text(
        text = "Partly cloudy",
        fontFamily = Urbanist,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 19.sp,
        letterSpacing = 0.25.sp,
        color = if (isDay) LightTextColor60 else DarkTextColor60,
    )
}

@Composable
private fun TemperatureDegree(temperature: Int, isDay: Boolean) {
    Text(
        text = "$temperature°C",
        fontFamily = Urbanist,
        fontWeight = FontWeight.SemiBold,
        fontSize = 64.sp,
        lineHeight = 77.sp,
        letterSpacing = 0.25.sp,
        color = if (isDay) LightTextColor else DarkTextColor,
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun animateAlignmentAsState(
    targetAlignment: Alignment,
): State<Alignment> {
    val biased = targetAlignment as BiasAlignment
    val horizontal by animateFloatAsState(
        biased.horizontalBias,
        animationSpec = tween(durationMillis = 500)
    )
    val vertical by animateFloatAsState(
        biased.verticalBias,
        animationSpec = tween(durationMillis = 500)
    )
    return derivedStateOf { BiasAlignment(horizontal, vertical) }
}