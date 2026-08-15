package com.example.checkoutdemo.steps

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.example.checkoutdemo.screens.CartScreen

class CartSteps(private val semanticsProvider: SemanticsNodeInteractionsProvider) {

    private val cartScreen: CartScreen
        get() = CartScreen(semanticsProvider)

    fun assertIsDisplayedCartFirstStep() {
        cartScreen.assertIsDisplayed()
    }

    fun assertQuantityPerItem(id: Int, quantity: String) {
        cartScreen.itemQuantity(id).assertTextEquals(quantity)
    }

    fun assertItemName(id: Int, name: String) {
        cartScreen.itemName(id).assertTextEquals(name)
    }

    fun assertSubTotal(subTotal: String) {
        cartScreen.cartSubtotal.assertTextEquals(subTotal)
    }

    fun assertTotal(total: String) {
        cartScreen.cartTotal.assertTextEquals(total)
    }

    fun assertIsDisplayedDiscountLine() {
        cartScreen.cartDiscount.assertIsDisplayed()
    }

    fun assertIsNotDisplayedDiscountLine() {
        cartScreen.cartDiscount.assertIsNotDisplayed()
    }

    fun assertPromoCodeLineContainsText(text: String) {
        cartScreen.promoMessage.assertTextContains(text)
    }

    fun assertIsEnabledCheckOutButton() {
        cartScreen.checkoutButton.assertIsEnabled()
    }

    fun assertIsNotEnabledCheckOutButton() {
        cartScreen.checkoutButton.assertIsNotEnabled()
    }

    fun assertIsEnabledMinusButton(id: Int) {
        cartScreen.itemMinus(id).assertIsEnabled()
    }

    fun assertIsNotEnabledMinusButton(id: Int) {
        cartScreen.itemMinus(id).assertIsNotEnabled()
    }

    fun assertIsEnabledPlusButton(id: Int) {
        cartScreen.itemPlus(id).assertIsEnabled()
    }

    fun assertIsNotEnabledPlusButton(id: Int) {
        cartScreen.itemPlus(id).assertIsNotEnabled()
    }

    fun assertItemIsNotOnTheScreen(id: Int) {
        cartScreen.cartItem(id).assertDoesNotExist()
    }

    fun assertCartTitle(title: String) {
        cartScreen.cartTitle.assertTextEquals(title)
    }

    fun assertCartEmptyMessage(message: String) {
        cartScreen.emptyCartMessage.assertTextEquals(message)
    }

    fun increaseItemCounter(id: Int) {
        cartScreen {
            itemPlus(id).performClick()
        }
    }

    fun decreaseItemCounter(id: Int) {
        cartScreen {
            itemMinus(id).performClick()
        }
    }

    fun removeItem(id: Int) {
        cartScreen {
            itemRemove(id).performClick()
        }
    }

    fun applyPromoCode(promoCode: String) {
        cartScreen {
            promoInput.performClick()
            promoInput.performTextInput(promoCode)
            promoApply.performClick()
        }
    }

    fun goToCheckout() {
        cartScreen {
            checkoutButton.performClick()
        }
    }
}