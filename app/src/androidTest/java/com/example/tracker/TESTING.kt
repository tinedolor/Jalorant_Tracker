package com.example.tracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.tracker.ui.theme.TrackerTheme
import org.junit.Rule
import org.junit.Test

class TESTING {

    @get:Rule

    val ComposeTestRule = createComposeRule()


    @Test

    fun testingtime(){
        ComposeTestRule.setContent {
            TrackerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    ProfileTab()

                }
            }
        }
    }
}