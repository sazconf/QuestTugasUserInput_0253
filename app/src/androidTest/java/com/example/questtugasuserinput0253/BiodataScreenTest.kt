package com.example.questtugasuserinput0253

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class BiodataScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun rendersFormAndDisabledProceedInitially() {
        composeRule.onNodeWithText("Form Biodata").assertIsDisplayed()
        composeRule.onNodeWithText("Proceed").assertIsNotEnabled()
    }

    @Test
    fun skillsCanBeSelectedIndependently() {
        val androidCheckbox = composeRule.onNodeWithTag("skill_android")
        val webCheckbox = composeRule.onNodeWithTag("skill_web")
        androidCheckbox.performClick()
        webCheckbox.performClick()

        androidCheckbox.assertIsOn()
        webCheckbox.assertIsOn()
    }
}
