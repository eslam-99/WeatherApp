package com.example.weatherapp.domain.model

data class Weather(
    val currentTemperature: Int,
    val currentDayWeatherStatus: CurrentDayWeatherStatus,
    val hourlyStatus: List<HourlyStatus>,
    val currentDayForecast: DayForecast,
    val nextDaysForecast: List<DayForecast>,
    val isDay: Boolean,
)
