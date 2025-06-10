package com.example.weatherapp.domain.model

data class HourlyStatus(
    val hour: Int,
    val temperature: Int,
    val weatherCode: Int,
)