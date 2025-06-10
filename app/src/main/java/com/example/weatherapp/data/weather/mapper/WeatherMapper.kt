package com.example.weatherapp.data.weather.mapper

import android.annotation.SuppressLint
import com.example.weatherapp.data.weather.model.WeatherDto
import com.example.weatherapp.domain.model.CurrentDayWeatherStatus
import com.example.weatherapp.domain.model.DayForecast
import com.example.weatherapp.domain.model.HourlyStatus
import com.example.weatherapp.domain.model.Weather
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun WeatherDto.toWeather(): Weather {
    val hourlyWeather = hourly
    val currentWeather = current
    val dailyWeather = daily
    val hourlyTime = hourlyWeather.time
    val hourlyTemperatures = hourlyWeather.temperature2m
    val hourlyWeatherCode = hourlyWeather.weatherCode
    val weatherCode = currentWeather.weatherCode
    val currentDayForecast = DayForecast(
        minTemperature = dailyWeather.temperature2mMin[0].roundToInt(),
        maxTemperature = dailyWeather.temperature2mMax[0].roundToInt(),
        weatherConditionCode = weatherCode,
    )
    val currentDayWeatherStatus = CurrentDayWeatherStatus(
        windSpeed = currentWeather.windSpeed10m.roundToInt(),
        humidity = currentWeather.relativeHumidity2m.roundToInt(),
        rain = currentWeather.precipitationProbability.roundToInt(),
        uvIndex = currentWeather.uvIndex.roundToInt(),
        pressure = currentWeather.pressureMsl.roundToInt(),
        feelsLike = currentWeather.apparentTemperature.roundToInt(),
    )
    val hourlyStatus = hourlyTemperatures.take(24)
        .zip(hourlyTime.zip(hourlyWeatherCode) { time, code -> Pair(time, code) }) { temp, pair ->
            HourlyStatus(
                hour = getHourFromTimeString(pair.first),
                temperature = temp.roundToInt(),
                weatherCode = pair.second,
            )
        }
    val nextDaysForecast = dailyWeather
        .weatherCode.drop(1)
        .zip(
            dailyWeather.temperature2mMin.drop(1)
                .zip(dailyWeather.temperature2mMax.drop(1)) { min, max ->
                    Pair(min, max)
                }) { code, minMax ->
            DayForecast(
                minTemperature = minMax.first.roundToInt(),
                maxTemperature = minMax.second.roundToInt(),
                weatherConditionCode = code,
            )
        }

    return Weather(
        currentTemperature = currentWeather.temperature2m.toInt(),
        currentDayForecast = currentDayForecast,
        currentDayWeatherStatus = currentDayWeatherStatus,
        hourlyStatus = hourlyStatus,
        nextDaysForecast = nextDaysForecast,
        isDay = true,
//        isDay = currentWeather.isDay == 1,
    )
}

@SuppressLint("NewApi")
private fun getHourFromTimeString(time: String): Int {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val dateTime = LocalDateTime.parse(time, formatter)
    return dateTime.hour
}

//    private fun getWeatherForeCast(weatherCode: Int): WeatherCondition {
//        return when (weatherCode) {
//            0 -> WeatherCondition.CLEAR_SKY
//            1 -> WeatherCondition.MAINLY_CLEAR
//            2 -> WeatherCondition.PARTLY_CLOUDY
//            3 -> WeatherCondition.OVERCAST
//            45 -> WeatherCondition.FOG
//            48 -> WeatherCondition.DEPOSITING_RIME_FOG
//            51 -> WeatherCondition.DRIZZLE_LIGHT
//            53 -> WeatherCondition.DRIZZLE_MODERATE
//            55 -> WeatherCondition.DRIZZLE_HIGH
//            56 -> WeatherCondition.FREEZING_DRIZZLE_LIGHT
//            57 -> WeatherCondition.FREEZING_DRIZZLE_HIGH
//            61 -> WeatherCondition.RAIN_LIGHT
//            63 -> WeatherCondition.RAIN_MODERATE
//            65 -> WeatherCondition.RAIN_HEAVY
//            66 -> WeatherCondition.FREEZING_RAIN_LIGHT
//            67 -> WeatherCondition.FREEZING_RAIN_HIGH
//            71 -> WeatherCondition.SNOW_LIGHT
//            73 -> WeatherCondition.SNOW_MODERATE
//            75 -> WeatherCondition.SNOW_HEAVY
//            77 -> WeatherCondition.SNOW_GRAINS
//            80 -> WeatherCondition.RAIN_SHOWER_LIGHT
//            81 -> WeatherCondition.RAIN_SHOWER_MODERATE
//            82 -> WeatherCondition.RAIN_SHOWER_HEAVY
//            85 -> WeatherCondition.SNOW_SHOWER_LIGHT
//            86 -> WeatherCondition.SNOW_SHOWER_HEAVY
//            95 -> WeatherCondition.THUNDER_STORM
//            96 -> WeatherCondition.THUNDER_STORM_HAIL_LIGHT
//            99 -> WeatherCondition.THUNDER_STORM_HAIL_HEAVY
//            else -> WeatherCondition.UNKNOWN_WEATHER_FORECAST
//        }
//    }
