package com.example.weatherapp.data.weather.repository

import com.example.weatherapp.data.weather.mapper.toWeather
import com.example.weatherapp.data.weather.model.WeatherDto
import com.example.weatherapp.domain.model.LocationCoordinate
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class WeatherRepositoryImpl(
    private val client: HttpClient,
    private val json: Json,
) : WeatherRepository {
    override suspend fun getDailyWeatherByCoordinate(locationCoordinate: LocationCoordinate): Weather {
        val response = client.get(BASE_WEATHER_URL) {
            url {
                parameters.append("latitude", locationCoordinate.latitude.toString())
                parameters.append("longitude", locationCoordinate.longitude.toString())
                parameters.append("daily", "weather_code,temperature_2m_max,temperature_2m_min")
                parameters.append("hourly", "temperature_2m,weather_code")
                parameters.append(
                    "current",
                    "temperature_2m,wind_speed_10m,relative_humidity_2m,precipitation_probability,uv_index,pressure_msl,is_day,weather_code,apparent_temperature"
                )
                parameters.append("forecast_days", "8")
            }
            /*
            https://api.open-meteo.com/v1/forecast?
            latitude=28.3&
            longitude=30&
            daily=temperature_2m_max,temperature_2m_min,weather_code&
            hourly=temperature_2m&
            current=temperature_2m,wind_speed_10m,relative_humidity_2m,precipitation_probability,uv_index,pressure_msl,is_day,weather_code,apparent_temperature&
            forecast_days=8
             */
        }
        val data = json.decodeFromString<WeatherDto>(response.bodyAsText())
        return data.toWeather()
    }

    companion object {
        private const val BASE_WEATHER_URL = "https://api.open-meteo.com/v1/forecast"
    }
}