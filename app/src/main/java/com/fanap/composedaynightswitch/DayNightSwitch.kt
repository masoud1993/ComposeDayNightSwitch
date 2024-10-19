package com.fanap.composedaynightswitch

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.fanap.composedaynightswitch.ui.theme.DayBackground
import com.fanap.composedaynightswitch.ui.theme.NightBackground

@Preview
@Composable
fun DayNightSwitch(modifier: Modifier = Modifier,
                   initialMode: Boolean = true,
    mode: (Boolean) -> Unit = {}) {

    val rectangleWidth = 420F
    val rectangleHeight = 195F
    val margin = 15f
    val diameter = 165
    val radius = 82.5
    val cornerRadius = 110F
    val translateXValue = rectangleWidth - diameter - 2 * margin

    val earthBitmap = ImageBitmap.imageResource(id = R.drawable.earth)
    val moonBitmap = ImageBitmap.imageResource(id = R.drawable.moon)

    var isOn by remember { mutableStateOf(initialMode) }

    val offsetX by animateFloatAsState(
        targetValue = if (isOn) 0f else 0.1f,
        animationSpec = keyframes {
            0f at 0 using LinearOutSlowInEasing
            10f at 100
            -10f at 200
            5f at 200
            0f at 400

            durationMillis = 400
        }, label = ""
    )


    val translationXAnim by animateFloatAsState(
        targetValue = if (isOn) 0f else translateXValue,
        animationSpec  = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = ""
    )

    val rotationAngleAnim by animateFloatAsState(
        targetValue = if (isOn) 0f else 360f,
        animationSpec = tween(durationMillis = 500, easing = EaseInOut),
        label = ""
    )

        Canvas(modifier = modifier
            .clickable {
                isOn = !isOn
                mode(isOn)
            }
            .testTag("SwitchCanvas")
            .offset {
                IntOffset(offsetX.dp.toPx().toInt(), 0)
            }
            .fillMaxWidth()
            .background(color = if(isOn) DayBackground else NightBackground)) {
            val width = size.width
            val height = size.height

            val topLeftX = (width - rectangleWidth) / 2
            val topLeftY = (height - rectangleHeight) / 2


            val startPosition = Offset(topLeftX + margin, topLeftY + margin)

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

            drawPath(switchPath,Color.White)

            translate(left = translationXAnim){

                rotate(rotationAngleAnim, pivot = Offset((topLeftX + margin + radius).toFloat(),
                    (topLeftY + margin + radius).toFloat()
                )){

                    if (isOn){
                        drawImage(earthBitmap,
                            topLeft = startPosition
                        )
                    }else{
                        drawImage(moonBitmap,
                            topLeft = startPosition
                        )
                    }
                }

            }
        }

}