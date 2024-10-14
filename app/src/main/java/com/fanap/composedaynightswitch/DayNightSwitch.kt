package com.fanap.composedaynightswitch

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.fanap.composedaynightswitch.ui.theme.DayBackground
import com.fanap.composedaynightswitch.ui.theme.NightBackground

@Preview
@Composable
fun DayNightSwitch(modifier: Modifier = Modifier,
    dayMode: Boolean = true) {

    val rectangleWidth = 430F
    val rectangleHeight = 180F

        Canvas(modifier = modifier
            .testTag("")
            .fillMaxSize()
            .background(color = if(dayMode) DayBackground else NightBackground)) {
            val width = size.width
            val height = size.height

            val topLeftX = (width - rectangleWidth) / 2
            val topLeftY = (height - rectangleHeight) / 2

            drawRoundRect(color = Color.Black,
                topLeft = Offset(topLeftX, topLeftY),
                size = Size(width = rectangleWidth, height = rectangleHeight),
                cornerRadius = CornerRadius(100F))

        }

}