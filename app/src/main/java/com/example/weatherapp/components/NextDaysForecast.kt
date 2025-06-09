package com.example.weatherapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.DarkBackgroundColor70
import com.example.weatherapp.ui.theme.DarkBorderColor
import com.example.weatherapp.ui.theme.DarkTextColor
import com.example.weatherapp.ui.theme.LightBackgroundColor70
import com.example.weatherapp.ui.theme.LightBorderColor
import com.example.weatherapp.ui.theme.LightTextColor
import com.example.weatherapp.ui.theme.Urbanist
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("NewApi")
@Composable
fun NextDaysForecast(isDay: Boolean) {
    Text(
        text = "Next 7 days",
        fontFamily = Urbanist,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.25.sp,
        color = if (isDay) LightTextColor else DarkTextColor,
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 12.dp),
    )
    Column(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 32.dp)
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 1.dp,
                color = if (isDay) LightBorderColor else DarkBorderColor,
                shape = RoundedCornerShape(24.dp)
            )
            .background(if (isDay) LightBackgroundColor70 else DarkBackgroundColor70)
            .padding(vertical = 8.dp)
    ) {
        val today = LocalDate.now()
        val next7Days = (1..7).map { daysToAdd ->
            today
                .plusDays(daysToAdd.toLong())
                .dayOfWeek
                .getDisplayName(TextStyle.FULL, Locale.getDefault())
        }
        val next7Resources = listOf(
            painterResource(R.drawable.day_clear_sky),
            painterResource(R.drawable.day_partly_cloudy),
            painterResource(R.drawable.day_partly_cloudy),
            painterResource(R.drawable.day_clear_sky),
            painterResource(R.drawable.day_overcast),
            painterResource(R.drawable.day_clear_sky),
            painterResource(R.drawable.day_overcast),
        )
        val maxTemp = 32
        val minTemp = 20
        next7Days.forEachIndexed { index, day ->
            DaysTableRow(day, next7Resources[index], maxTemp, minTemp, isDay)
            if (index != next7Days.size - 1)
                DaysTableDivider(isDay)
        }
    }
}
