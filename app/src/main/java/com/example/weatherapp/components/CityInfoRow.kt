package com.example.weatherapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.DarkBackgroundGradiant
import com.example.weatherapp.ui.theme.DarkElementBackgroundColor
import com.example.weatherapp.ui.theme.DarkTextColor
import com.example.weatherapp.ui.theme.LightBackgroundGradiant
import com.example.weatherapp.ui.theme.LightElementBackgroundColor
import com.example.weatherapp.ui.theme.LightTextColor
import com.example.weatherapp.ui.theme.Urbanist

@Composable
fun CityInfoRow(isDay: Boolean, scrollProgress: Float) {
    Column(
        Modifier
            .background(
                (if (isDay) LightBackgroundGradiant.first() else DarkBackgroundGradiant.first()).copy(
                    alpha = scrollProgress
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp - (12 * scrollProgress).dp),
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.location),
                    contentDescription = "location",
                    tint = if (isDay) LightElementBackgroundColor else DarkElementBackgroundColor,
                    modifier = Modifier.size(24.dp),
                )
                Text(
                    text = "Baghdad",
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp,
                    color = if (isDay) LightTextColor else DarkTextColor,
                    modifier = Modifier.padding(start = 4.dp),
                )
            }
        }
        Box(Modifier.padding(bottom = (12 * scrollProgress).dp))
    }
}
