package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.components.CityInfoRow
import com.example.weatherapp.components.CurrentDayWeather
import com.example.weatherapp.components.NextDaysForecast
import com.example.weatherapp.components.TodayHourlyStatus
import com.example.weatherapp.components.WeatherStatusGrid
import com.example.weatherapp.domain.model.CurrentDayWeatherStatus
import com.example.weatherapp.ui.model.DayForecastUiModel
import com.example.weatherapp.ui.model.HourlyStatusUiModel
import com.example.weatherapp.ui.model.WeatherCondition
import com.example.weatherapp.ui.model.WeatherUiModel
import com.example.weatherapp.ui.theme.DarkBackgroundGradiant
import com.example.weatherapp.ui.theme.LightBackgroundGradiant
import com.example.weatherapp.ui.utils.calculateScrollProgress
import com.example.weatherapp.viewmodel.HomeScreenViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@SuppressLint("NewApi")
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val state = viewModel.state.collectAsState()
    HomeContent(state.value)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(weatherUiModel: WeatherUiModel?, modifier: Modifier = Modifier) {
    val isDay = false
//    val isDay = weatherUiModel?.isDay ?: true
    val lazyListState = rememberLazyListState()
    val animationTriggerHeight = 150f
    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .background(Brush.linearGradient(if (isDay) LightBackgroundGradiant else DarkBackgroundGradiant))
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        stickyHeader {
            val scrollProgress = calculateScrollProgress(lazyListState, animationTriggerHeight)
            CityInfoRow(weatherUiModel?.cityName, isDay, scrollProgress)
        }
        item {
            val scrollProgress = calculateScrollProgress(lazyListState, animationTriggerHeight)
            CurrentDayWeather(
                scrollProgress = scrollProgress,
                isDay = isDay,
                currentTemperature = weatherUiModel?.currentTemperature,
                todayForecastUiModel = weatherUiModel?.currentDayForecastUiModel
            )
        }
        item {
            WeatherStatusGrid(
                isDay = isDay,
                todayWeatherStatus = weatherUiModel?.currentDayWeatherStatus
            )
        }
        item {
            TodayHourlyStatus(isDay = isDay, hourlyStatus = weatherUiModel?.hourlyStatus)
        }
        item {
            NextDaysForecast(isDay = isDay, nextDaysForecast = weatherUiModel?.nextDaysForecast)
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun HomeScreenPreview() {
    val weatherUiModel = WeatherUiModel(
        cityName = "Cairo",
        currentDayWeatherStatus = CurrentDayWeatherStatus(
            windSpeed = 25,
            humidity = 32,
            rain = 30,
            uvIndex = 40,
            pressure = 50,
            feelsLike = 60,
        ),
        nextDaysForecast = listOf(
            DayForecastUiModel(
                minTemperature = 20,
                maxTemperature = 32,
                weatherCondition = WeatherCondition.CLEAR_SKY,
            ),
            DayForecastUiModel(
                minTemperature = 20,
                maxTemperature = 32,
                weatherCondition = WeatherCondition.CLEAR_SKY,
            ),
            DayForecastUiModel(
                minTemperature = 20,
                maxTemperature = 32,
                weatherCondition = WeatherCondition.CLEAR_SKY,
            ),
            DayForecastUiModel(
                minTemperature = 20,
                maxTemperature = 32,
                weatherCondition = WeatherCondition.CLEAR_SKY,
            ),
            DayForecastUiModel(
                minTemperature = 20,
                maxTemperature = 32,
                weatherCondition = WeatherCondition.CLEAR_SKY,
            ),
            DayForecastUiModel(
                minTemperature = 20,
                maxTemperature = 32,
                weatherCondition = WeatherCondition.CLEAR_SKY,
            ),
            DayForecastUiModel(
                minTemperature = 20,
                maxTemperature = 32,
                weatherCondition = WeatherCondition.CLEAR_SKY,
            ),
            DayForecastUiModel(
                minTemperature = 20,
                maxTemperature = 32,
                weatherCondition = WeatherCondition.CLEAR_SKY,
            ),
        ),
        hourlyStatus = listOf(
            HourlyStatusUiModel(
                hour = 0,
                temperature = 20,
                weatherCondition = WeatherCondition.CLEAR_SKY,
            ),
        ),
        currentTemperature = 24,
        currentDayForecastUiModel = DayForecastUiModel(
            minTemperature = 20,
            maxTemperature = 32,
            weatherCondition = WeatherCondition.CLEAR_SKY,
        ),
        isDay = true,
    )
    HomeContent(weatherUiModel)
}
