package com.example.weatherapp.data.weather.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
    @SerialName("apparent_temperature")
    val apparentTemperature: Double,
    @SerialName("interval")
    val interval: Int,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("precipitation_probability")
    val precipitationProbability: Double,
    @SerialName("pressure_msl")
    val pressureMsl: Double,
    @SerialName("relative_humidity_2m")
    val relativeHumidity2m: Double,
    @SerialName("temperature_2m")
    val temperature2m: Double,
    @SerialName("time")
    val time: String,
    @SerialName("uv_index")
    val uvIndex: Double,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("wind_speed_10m")
    val windSpeed10m: Double
)