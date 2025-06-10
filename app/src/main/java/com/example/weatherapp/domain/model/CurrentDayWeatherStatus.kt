package com.example.weatherapp.domain.model

data class CurrentDayWeatherStatus(
    val windSpeed: Int,
    val humidity: Int,
    val rain: Int,
    val uvIndex: Int,
    val pressure: Int,
    val feelsLike: Int,
)