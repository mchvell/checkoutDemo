package com.example.checkoutdemo.screens

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class SuccessScreen(semanticsProvider: SemanticsNodeInteractionsProvider):
    ComposeScreen<SuccessScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction =  { hasTestTag(SuccessTags.ROOT) }
    ){
    val newOrderButton: KNode = child { hasTestTag(SuccessTags.NEW_ORDER_BUTTON) }
    val successPaymentMethod: KNode = child { hasTestTag(SuccessTags.SUCCESS_PAYMENT_METHOD) }
    val successTotal: KNode = child { hasTestTag(SuccessTags.SUCCESS_TOTAL) }
    val successMessage: KNode = child { hasTestTag(SuccessTags.SUCCESS_MESSAGE) }
}