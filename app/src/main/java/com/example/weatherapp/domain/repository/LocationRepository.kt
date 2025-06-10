package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.Location

interface LocationRepository {
    suspend fun getLocation(): Location
}