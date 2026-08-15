package com.example.checkoutdemo.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.checkoutdemo.MainActivity
import com.example.checkoutdemo.steps.CartSteps
import org.junit.Rule
import org.junit.Test

class CartTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val cartSteps = CartSteps(composeTestRule)

    @Test
    fun cartTitleTest() {
        cartSteps.assertCartTitle("Корзина")
    }
}