package com.fanap.composedaynightswitch

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.fanap.composedaynightswitch.ui.theme.DayBackground
import com.fanap.composedaynightswitch.ui.theme.NightBackground

@Preview
@Composable
fun DayNightSwitch(modifier: Modifier = Modifier,
    dayMode: Boolean = true) {

    val earthBitmap = ImageBitmap.imageResource(id = R.drawable.earth)
    val moonBitmap = ImageBitmap.imageResource(id = R.drawable.moon)


    val rectangleWidth = 420F
    val rectangleHeight = 195F

        Canvas(modifier = modifier
            .testTag("SwitchCanvas")
            .fillMaxSize()
            .background(color = if(dayMode) DayBackground else NightBackground)) {
            val width = size.width
            val height = size.height

            val topLeftX = (width - rectangleWidth) / 2
            val topLeftY = (height - rectangleHeight) / 2
            val cornerRadius = 110F

            val switchPath = Path().apply {

                moveTo(topLeftX + cornerRadius, topLeftY)
                lineTo(topLeftX + rectangleWidth - cornerRadius, topLeftY)

                quadraticTo(
                    topLeftX + rectangleWidth, topLeftY,
                    topLeftX + rectangleWidth, topLeftY + cornerRadius
                )

                lineTo(topLeftX + rectangleWidth, topLeftY + rectangleHeight - cornerRadius)

                quadraticTo(
                    topLeftX + rectangleWidth, topLeftY + rectangleHeight,
                    topLeftX + rectangleWidth - cornerRadius, topLeftY + rectangleHeight
                )

                lineTo(topLeftX + cornerRadius, topLeftY + rectangleHeight)

                quadraticTo(
                    topLeftX, topLeftY + rectangleHeight,
                    topLeftX, topLeftY + rectangleHeight - cornerRadius
                )

                lineTo(topLeftX, topLeftY + cornerRadius)

                quadraticTo(
                    topLeftX, topLeftY,
                    topLeftX + cornerRadius, topLeftY
                )

                close()

            }

            drawPath(switchPath, if(dayMode) Color.White else Color.Black)


            drawImage(earthBitmap,
                topLeft = Offset(topLeftX, topLeftY)
                )

//            drawImage(moonBitmap,
//                topLeft = Offset(topLeftX + rectangleWidth - 190, topLeftY)
//            )

        }

}