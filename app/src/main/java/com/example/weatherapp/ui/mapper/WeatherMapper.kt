package com.example.weatherapp.ui.mapper

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.ui.model.DayForecastUiModel
import com.example.weatherapp.ui.model.HourlyStatusUiModel
import com.example.weatherapp.ui.model.WeatherCondition
import com.example.weatherapp.ui.model.WeatherUiModel

fun Weather.toWeatherUiModel(cityName: String): WeatherUiModel {
    val currentDayForecastUiModel = DayForecastUiModel(
        minTemperature = currentDayForecast.minTemperature,
        maxTemperature = currentDayForecast.maxTemperature,
        weatherCondition = WeatherCondition.getWeatherCondition(currentDayForecast.weatherConditionCode),
    )
    val nextDaysForecast = nextDaysForecast.map { dayForecast ->
        DayForecastUiModel(
            minTemperature = dayForecast.minTemperature,
            maxTemperature = dayForecast.maxTemperature,
            weatherCondition = WeatherCondition.getWeatherCondition(dayForecast.weatherConditionCode),
        )
    }
    val hourlyStatus = hourlyStatus.map {
        HourlyStatusUiModel(
            hour = it.hour,
            temperature = it.temperature,
            weatherCondition = WeatherCondition.getWeatherCondition(it.weatherCode),
        )
    }
    return WeatherUiModel(
        cityName = cityName,
        nextDaysForecast = nextDaysForecast,
        currentTemperature = currentTemperature,
        currentDayForecastUiModel = currentDayForecastUiModel,
        currentDayWeatherStatus = currentDayWeatherStatus,
        hourlyStatus = hourlyStatus,
        isDay = isDay,
    )
}