package com.example.weatherapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.DarkBorderColor
import com.example.weatherapp.ui.theme.LightBorderColor

@Composable
fun DaysTableDivider(isDay: Boolean) {
    HorizontalDivider(
        color = if (isDay) LightBorderColor else DarkBorderColor,
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 10.dp)
    )
}
