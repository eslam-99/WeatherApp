package com.example.weatherapp.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.components.CityInfoRow
import com.example.weatherapp.components.CurrentDayWeatherTest
import com.example.weatherapp.components.NextDaysForecast
import com.example.weatherapp.components.TodayHourlyTemperature
import com.example.weatherapp.components.WeatherStatusGrid
import com.example.weatherapp.ui.theme.DarkBackgroundGradiant
import com.example.weatherapp.ui.theme.LightBackgroundGradiant
import com.example.weatherapp.utils.calculateScrollProgress

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@SuppressLint("NewApi")
@Composable
fun HomeScreen() {
    val isDay = false
    val lazyListState = rememberLazyListState()
    val animationTriggerHeight = 150f
    SharedTransitionLayout {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .background(Brush.linearGradient(if (isDay) LightBackgroundGradiant else DarkBackgroundGradiant))
                .windowInsetsPadding(WindowInsets.statusBars)
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            stickyHeader {
                val scrollProgress = calculateScrollProgress(lazyListState, animationTriggerHeight)
                CityInfoRow(isDay, scrollProgress)
            }
            item {
                val scrollProgress = calculateScrollProgress(lazyListState, animationTriggerHeight)
                CurrentDayWeatherTest(scrollProgress = scrollProgress, isDay = isDay)
            }
            item {
                WeatherStatusGrid(isDay)
            }
            item {
                TodayHourlyTemperature(isDay)
            }
            item {
                NextDaysForecast(isDay)
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
