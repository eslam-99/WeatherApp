package com.example.weatherapp.ui.model

data class HourlyStatusUiModel(
    val hour: Int,
    val temperature: Int,
    val weatherCondition: WeatherCondition,
)