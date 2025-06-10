package com.example.weatherapp.di

import com.example.weatherapp.data.location.repository.LocationRepositoryImpl
import com.example.weatherapp.data.weather.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.repository.LocationRepository
import com.example.weatherapp.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val repositoryModule = module {
    // repositories
    single<LocationRepository> { LocationRepositoryImpl(get(), get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
    // json serializable
    single { Json { ignoreUnknownKeys = true; isLenient = true } }
    // network
    single<HttpClient> {
        HttpClient(CIO) {
            engine {
                requestTimeout = 5000
            }
            install(ContentNegotiation) {
                json(get())
            }
        }
    }
}