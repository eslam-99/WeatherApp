package com.example.weatherapp.ui.utils

import androidx.compose.ui.graphics.Color
import com.example.weatherapp.R
import com.example.weatherapp.ui.model.WeatherCondition

fun WeatherCondition?.getDrawable(isDay: Boolean): Int {
    return (if (isDay) this?.dayDrawableId
    else this?.nightDrawableId)
        ?: R.drawable.day_clear_sky
}

fun WeatherCondition?.getShadow(isDay: Boolean): Color {
    return (if (isDay) this?.dayShadowColor
    else this?.nightShadowColor)
        ?: Color(0xFFFFC701).copy(alpha = 0.5f)
}
