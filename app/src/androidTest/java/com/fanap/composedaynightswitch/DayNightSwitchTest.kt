package com.fanap.composedaynightswitch

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fanap.composedaynightswitch.ui.theme.ComposeDayNightSwitchTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DayNightSwitchTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenSwitchIsOnDayModeBackgroundShouldBeWhite() {

        val dayMode = true

        composeTestRule.setContent {
            ComposeDayNightSwitchTheme {
                DayNightSwitch(modifier = Modifier, dayMode){

                }
            }
        }

        composeTestRule.onNodeWithTag("SwitchCanvas").assertIsOn()
    }
}