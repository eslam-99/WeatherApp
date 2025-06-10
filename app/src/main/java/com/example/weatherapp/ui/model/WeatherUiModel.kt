package com.example.weatherapp.ui.model

import com.example.weatherapp.domain.model.CurrentDayWeatherStatus

data class WeatherUiModel(
    val cityName: String,
    val currentTemperature: Int,
    val currentDayForecastUiModel: DayForecastUiModel,
    val currentDayWeatherStatus: CurrentDayWeatherStatus,
    val hourlyStatus: List<HourlyStatusUiModel>,
    val nextDaysForecast: List<DayForecastUiModel>,
    val isDay: Boolean,
)