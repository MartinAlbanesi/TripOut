package com.example.turistaapp.welcome.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertValueEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class WelcomeScreenKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenTextFieldNotIsSnakeCase_thenErrorVisible() {
        composeTestRule.setContent {
            WelcomeScreen(onClickSaveName = {})
        }

        composeTestRule.onNodeWithTag("errorText").assertExists()

//        composeTestRule.onNodeWithTag("nameTextField").performTextInput("test")
    }
}