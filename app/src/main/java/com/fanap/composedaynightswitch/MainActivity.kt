package com.fanap.composedaynightswitch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fanap.composedaynightswitch.ui.theme.ComposeDayNightSwitchTheme
import com.fanap.composedaynightswitch.ui.theme.DayBackground
import com.fanap.composedaynightswitch.ui.theme.NightBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDayNightSwitchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    var isOn by remember {
                        mutableStateOf(true)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(if (isOn) DayBackground else NightBackground)
                    ) {

                        DayNightSwitch(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(innerPadding), isOn
                        ) {
                            isOn = it
                        }

                        Text(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 200.dp),
                            color = if (isOn) NightBackground else DayBackground,
                            fontSize = 45.sp,
                            fontWeight = FontWeight.Bold,
                            text = if (isOn) "DAY" else "NIGHT"
                        )

                    }
                }
            }
        }
    }
}