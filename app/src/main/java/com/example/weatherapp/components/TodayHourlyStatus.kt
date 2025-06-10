package com.example.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.model.HourlyStatusUiModel
import com.example.weatherapp.ui.theme.DarkBackgroundColor70
import com.example.weatherapp.ui.theme.DarkBorderColor
import com.example.weatherapp.ui.theme.DarkTextColor
import com.example.weatherapp.ui.theme.DarkTextColor60
import com.example.weatherapp.ui.theme.DarkTextColor87
import com.example.weatherapp.ui.theme.LightBackgroundColor70
import com.example.weatherapp.ui.theme.LightBorderColor
import com.example.weatherapp.ui.theme.LightTextColor
import com.example.weatherapp.ui.theme.LightTextColor60
import com.example.weatherapp.ui.theme.LightTextColor87
import com.example.weatherapp.ui.theme.Urbanist
import com.example.weatherapp.ui.utils.getDrawable
import com.example.weatherapp.ui.utils.getShadow

@Composable
fun TodayHourlyStatus(isDay: Boolean, hourlyStatus: List<HourlyStatusUiModel>?) {
    Text(
        text = "Today",
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
    LazyRow(
        modifier = Modifier.padding(top = 12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(hourlyStatus?.size ?: 0) { index ->
            val hourStatus = hourlyStatus?.get(index)
            HourlyStatusCard(isDay, hourStatus)
        }
    }
}

@Composable
private fun HourlyStatusCard(
    isDay: Boolean,
    hourStatus: HourlyStatusUiModel?
) {
    Box {
        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .width(88.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = if (isDay) LightBorderColor else DarkBorderColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(if (isDay) LightBackgroundColor70 else DarkBackgroundColor70)
                .padding(top = 55.dp, bottom = 16.dp)
                .padding(horizontal = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "${hourStatus?.temperature ?: "-"}°C",
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    letterSpacing = 0.25.sp,
                    color = if (isDay) LightTextColor87 else DarkTextColor87,
                )
                Text(
                    text = "${hourStatus?.hour ?: "-"}:00",
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    letterSpacing = 0.25.sp,
                    color = if (isDay) LightTextColor60 else DarkTextColor60,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }
        Box(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(hourStatus?.weatherCondition.getDrawable(isDay)),
                contentDescription = "forecast",
                modifier = Modifier
                    .height(58.dp)
                    .align(Alignment.TopCenter)
                    .dropShadow(
                        color = hourStatus?.weatherCondition.getShadow(isDay).copy(alpha = 0.1f),
                        blur = 40.dp,
                        offsetY = 5.dp,
                        offsetX = 0.dp,
                        spread = 15.dp,
                        shape = CircleShape,
                    )
            )
        }
    }
}
