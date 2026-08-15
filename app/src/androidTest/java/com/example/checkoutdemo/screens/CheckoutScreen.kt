package com.example.checkoutdemo.screens

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode


class CheckoutScreen(semanticsProvider: SemanticsNodeInteractionsProvider):
    ComposeScreen<CheckoutScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(CheckoutTags.ROOT) }
) {
    val paymentCard: KNode = child { hasTestTag(CheckoutTags.PAYMENT_CARD) }
    val paymentSbp: KNode = child { hasTestTag(CheckoutTags.PAYMENT_SBP) }
    val paymentCash: KNode = child { hasTestTag(CheckoutTags.PAYMENT_CASH) }
    val paymentCashHint: KNode = child { hasTestTag(CheckoutTags.PAYMENT_CASH_HINT) }
    val checkoutTotal: KNode = child { hasTestTag(CheckoutTags.CHECKOUT_TOTAL) }
    val payButton: KNode = child { hasTestTag(CheckoutTags.PAY_BUTTON) }
    val backButton: KNode = child { hasTestTag(CheckoutTags.BACK_BUTTON) }
}