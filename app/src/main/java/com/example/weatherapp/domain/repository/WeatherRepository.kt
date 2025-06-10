package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.LocationCoordinate
import com.example.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getDailyWeatherByCoordinate(locationCoordinate: LocationCoordinate): Weather
}