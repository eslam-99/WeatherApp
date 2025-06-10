package com.example.weatherapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BlurredEllipse(figmaBlurValue: Float, ellipseColor: Color, modifier: Modifier = Modifier) {
    val composeBlurRadius = (figmaBlurValue).dp

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size((figmaBlurValue / 2).dp)
                .blur(
                    radius = composeBlurRadius,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                )
                .background(
                    color = ellipseColor,
                    shape = CircleShape
                )
        )
    }
}