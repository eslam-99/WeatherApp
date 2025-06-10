package com.example.weatherapp.data.location.repository

import com.example.weatherapp.data.location.mapper.toLocation
import com.example.weatherapp.data.location.model.IpLocationDto
import com.example.weatherapp.domain.model.Location
import com.example.weatherapp.domain.repository.LocationRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class LocationRepositoryImpl(
    private val client: HttpClient,
    private val json: Json,
) : LocationRepository {
    override suspend fun getLocation(): Location {
        val response = client.get {
            url(BASE_IP_LOCATION_URL)
        }
        return json.decodeFromString<IpLocationDto>(response.bodyAsText()).toLocation()
    }

    companion object {
        private const val BASE_IP_LOCATION_URL = "http://ip-api.com/json"
    }
}