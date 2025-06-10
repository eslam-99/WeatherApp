package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.LocationRepository
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.ui.mapper.toWeatherUiModel
import com.example.weatherapp.ui.model.WeatherUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
    init {
        getWeatherData()
    }

    private val _state = MutableStateFlow<WeatherUiModel?>(null)
    val state = _state.asStateFlow()

    fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val location = locationRepository.getLocation()
                val weather = weatherRepository.getDailyWeatherByCoordinate(location.coordinate)
                val weatherUiModel = weather.toWeatherUiModel(location.city)
                _state.emit(weatherUiModel)
            } catch (exception: Exception) {
                println(exception)
            }
        }
    }
}