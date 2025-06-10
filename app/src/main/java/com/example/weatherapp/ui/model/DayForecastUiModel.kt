package com.example.weatherapp.ui.model

data class DayForecastUiModel(
    val minTemperature: Int,
    val maxTemperature: Int,
    val weatherCondition: WeatherCondition,
)