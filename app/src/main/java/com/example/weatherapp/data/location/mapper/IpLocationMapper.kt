package com.example.weatherapp.data.location.mapper

import com.example.weatherapp.data.location.model.IpLocationDto
import com.example.weatherapp.domain.exception.NoLocationFoundException
import com.example.weatherapp.domain.model.Location
import com.example.weatherapp.domain.model.LocationCoordinate

fun IpLocationDto.toLocation(): Location {
    val latitude = lat ?: throw NoLocationFoundException()
    val longitude = lon ?: throw NoLocationFoundException()
    return Location(
        city = city ?: throw NoLocationFoundException(),
        coordinate =
            LocationCoordinate(
                longitude = longitude,
                latitude = latitude,
            )
    )
}
