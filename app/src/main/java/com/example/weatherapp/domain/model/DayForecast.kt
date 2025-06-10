package com.example.weatherapp.domain.model

data class DayForecast(
    val minTemperature: Int,
    val maxTemperature: Int,
    val weatherConditionCode: Int,
)