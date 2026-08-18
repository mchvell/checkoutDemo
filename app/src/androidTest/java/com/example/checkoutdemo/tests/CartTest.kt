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

    @Test
    fun cartItemsQuantityOnStartTest() {
        cartSteps.assertQuantityPerItem(3, "2")
    }

    @Test
    fun cartItemQuantityAfterPlusTest() {
        cartSteps.increaseItemCounter(3)
        cartSteps.assertQuantityPerItem(3, "3")
    }

    @Test
    fun cartItemDecreaseTest() {
        cartSteps.removeItem(1)
        cartSteps.assertItemIsNotOnTheScreen(1)
    }

    @Test
    fun cartSubTotalTest() {
        cartSteps.assertSubTotal("Сумма: 5296,00 ₽")
    }

    @Test
    fun cartSubTotalAfterMinus() {
        cartSteps.decreaseItemCounter(2)
        cartSteps.assertSubTotal("Сумма: 2297,00 ₽")
    }

    @Test
    fun insertPromoTest() {
        cartSteps.applyPromoCode("SALE10")
        cartSteps.assertTotal("Итого: 4766,40 ₽")
    }

    @Test
    fun promoTextTest10() {
        cartSteps.applyPromoCode("SALE10")
        cartSteps.assertPromoCodeLineContainsText("Промокод применён: −10%")
    }

    @Test
    fun promoTextTest20() {
        cartSteps.applyPromoCode(" SALE20 ")
        cartSteps.assertPromoCodeLineContainsText("Промокод применён: −20%")
    }

    @Test
    fun cartItemQuantityAfterMinusTest() {
        cartSteps.decreaseItemCounter(3)
        cartSteps.assertQuantityPerItem(3, "1")
    }

    @Test
    fun cartIsEmpty() {
        for (i in 1..3) {
            cartSteps.removeItem(i)
        }
        cartSteps.assertCartEmptyMessage("Корзина пуста")
    }
}