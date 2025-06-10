package com.example.weatherapp.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.model.DayForecastUiModel
import com.example.weatherapp.ui.model.WeatherCondition
import com.example.weatherapp.ui.theme.DarkBackgroundGradiant
import com.example.weatherapp.ui.theme.DarkBorderColor
import com.example.weatherapp.ui.theme.DarkTextColor
import com.example.weatherapp.ui.theme.DarkTextColor60
import com.example.weatherapp.ui.theme.DarkTextColor87
import com.example.weatherapp.ui.theme.LightBackgroundGradiant
import com.example.weatherapp.ui.theme.LightBorderColor
import com.example.weatherapp.ui.theme.LightTextColor
import com.example.weatherapp.ui.theme.LightTextColor60
import com.example.weatherapp.ui.theme.LightTextColor87
import com.example.weatherapp.ui.theme.Urbanist
import com.example.weatherapp.ui.utils.getDrawable
import com.example.weatherapp.ui.utils.getShadow

@Composable
fun CurrentDayWeather(
    scrollProgress: Float,
    isDay: Boolean,
    currentTemperature: Int?,
    todayForecastUiModel: DayForecastUiModel?,
    modifier: Modifier = Modifier
) {
    val minHeight = 160.dp
    val maxHeight = 360.dp
    var isScrolled by remember { mutableStateOf(false) }
    isScrolled = scrollProgress > 0.5f
    var animatedTopPadding =
        animateDpAsState(if (isScrolled) 110.dp else 0.dp, animationSpec = tween(500))
    var animatedHeight =
        animateDpAsState(if (isScrolled) minHeight else maxHeight, animationSpec = tween(500))
    var animatedBlurValue =
        animateFloatAsState(if (isScrolled) 150f else 250f, animationSpec = tween(500))
    var animatedShadowValue =
        animateFloatAsState(if (isScrolled) 75f else 125f, animationSpec = tween(500))
    val forecastAlignment by animateAlignmentAsState(if (isScrolled) Alignment.CenterStart else Alignment.TopCenter)
    val weatherAlignment by animateAlignmentAsState(if (isScrolled) Alignment.CenterEnd else Alignment.BottomCenter)

    Box(
        modifier = Modifier
            .padding(top = animatedTopPadding.value)
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = modifier
                .height(animatedHeight.value)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = (if (isScrolled) 15 else 0).dp),
                contentAlignment = forecastAlignment,
            ) {
                Box(
                    modifier = Modifier
                        .padding(
                            top = (if (isScrolled) 15 else 50).dp,
                            start = (if (isScrolled) 30 else 0).dp,
                        )
                ) {
                    BlurredEllipse(
                        figmaBlurValue = animatedBlurValue.value,
                        ellipseColor = todayForecastUiModel?.weatherCondition.getShadow(isDay),
                    )
                }
                Box {
                    Image(
                        painter = painterResource(
                            todayForecastUiModel?.weatherCondition.getDrawable(
                                isDay
                            )
                        ),
                        contentDescription = "forecast",
                        contentScale = ContentScale.FillHeight,
                        modifier = if (isDay) Modifier
                            .height((if (isScrolled) 130 else 200).dp)
                            .dropShadow(
                                offsetX = -((animateFloatAsState(if (isScrolled) 10f else 25f)).value.dp),
                                offsetY = ((animateFloatAsState(if (isScrolled) 20f else 50f)).value.dp),
                                shape = CircleShape,
                                color = Color(0xFF1D2646).copy(0.2f),
                                blur = (animatedShadowValue.value / 3).dp,
                            )
                            .dropShadow(
                                offsetX = -((animateFloatAsState(if (isScrolled) 7f else 15f)).value.dp),
                                offsetY = ((animateFloatAsState(if (isScrolled) 5f else 0f)).value.dp),
                                shape = CircleShape,
                                color = Color(0xFF87CEFA).copy(0.2f),
                                blur = (animatedShadowValue.value / 3).dp,
                            )
                        else Modifier
                            .height((if (isScrolled) 130 else 200).dp)
                            .dropShadow(
                                offsetX = -((animateFloatAsState(if (isScrolled) 7f else 15f)).value.dp),
                                offsetY = ((animateFloatAsState(if (isScrolled) 5f else 0f)).value.dp),
                                shape = CircleShape,
                                color = Color(0xFFFFFFFF).copy(0.15f),
                                blur = (animatedShadowValue.value / 2).dp,
                            ),
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = weatherAlignment
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TemperatureDegree(currentTemperature, isDay)
                    WeatherCondition(todayForecastUiModel?.weatherCondition?.stateName, isDay)
                    DailyRangeTemperatures(
                        todayForecastUiModel?.minTemperature,
                        todayForecastUiModel?.maxTemperature,
                        isDay
                    )
                }
            }
        }
    }
}

@Composable
private fun DailyRangeTemperatures(
    minTemperature: Int?,
    maxTemperature: Int?,
    isDay: Boolean,
) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(color = if (isDay) LightBorderColor else DarkBorderColor)
            .padding(vertical = 8.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MinMaxTemperatures(maxTemperature, minTemperature, isDay)
    }
}

@Composable
private fun MinMaxTemperatures(maxTemperature: Int?, minTemperature: Int?, isDay: Boolean) {
    TemperatureMinMax(maxTemperature, painterResource(R.drawable.arrow_up), isDay)
    VerticalDivider(
        thickness = 1.dp,
        color = if (isDay) Color.Black.copy(alpha = 0.24f) else Color.White.copy(alpha = 0.24f),
        modifier = Modifier
            .height(14.dp)
            .padding(horizontal = 8.dp)
    )
    TemperatureMinMax(minTemperature, painterResource(R.drawable.arrow_down), isDay)
}

@Composable
private fun TemperatureMinMax(temperature: Int?, iconResource: Painter, isDay: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = iconResource,
            contentDescription = null,
            tint = if (isDay) LightTextColor else DarkTextColor,
            modifier = Modifier.size(12.dp),
        )
        Text(
            text = "${temperature ?: "-"}°C",
            fontFamily = Urbanist,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 19.sp,
            letterSpacing = 0.25.sp,
            color = if (isDay) LightTextColor87 else DarkTextColor87,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}

@Composable
private fun WeatherCondition(currentForecast: String?, isDay: Boolean) {
    Text(
        text = currentForecast ?: "-",
        fontFamily = Urbanist,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 19.sp,
        letterSpacing = 0.25.sp,
        color = if (isDay) LightTextColor60 else DarkTextColor60,
    )
}

@Composable
private fun TemperatureDegree(temperature: Int?, isDay: Boolean) {
    Text(
        text = "${temperature ?: "-"}°C",
        fontFamily = Urbanist,
        fontWeight = FontWeight.SemiBold,
        fontSize = 64.sp,
        lineHeight = 77.sp,
        letterSpacing = 0.25.sp,
        color = if (isDay) LightTextColor else DarkTextColor,
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun animateAlignmentAsState(
    targetAlignment: Alignment,
): State<Alignment> {
    val biased = targetAlignment as BiasAlignment
    val horizontal by animateFloatAsState(
        biased.horizontalBias,
        animationSpec = tween(durationMillis = 500)
    )
    val vertical by animateFloatAsState(
        biased.verticalBias,
        animationSpec = tween(durationMillis = 500)
    )
    return derivedStateOf { BiasAlignment(horizontal, vertical) }
}

@Preview(widthDp = 360, heightDp = 360)
@Composable
private fun CurrentDayWeatherPreview() {
    val isDay = true
    val bg = if (isDay) LightBackgroundGradiant.first() else DarkBackgroundGradiant.first()
    Box(
        Modifier.background(color = bg)
    ) {
        CurrentDayWeather(
            scrollProgress = 0f,
            isDay = isDay,
            currentTemperature = 24,
            todayForecastUiModel = DayForecastUiModel(
                minTemperature = 20,
                maxTemperature = 32,
                weatherCondition = WeatherCondition.MAINLY_CLEAR,
            ),
        )
    }
}