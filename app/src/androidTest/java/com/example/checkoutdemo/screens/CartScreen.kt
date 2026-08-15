package com.example.checkoutdemo.screens

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class CartScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<CartScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(CartTags.ROOT) }
    ) {
    val cartTitle: KNode = child {hasTestTag((CartTags.CART_TITLE))}
    val cartSubtotal: KNode = child { hasTestTag(CartTags.CART_SUBTOTAL) }
    val cartTotal: KNode = child {hasTestTag(CartTags.CART_TOTAL)}
    val cartDiscount: KNode = child { hasTestTag(CartTags.CART_DISCOUNT) }
    val promoInput: KNode = child { hasTestTag(CartTags.PROMO_INPUT) }
    val promoApply: KNode = child { hasTestTag(CartTags.PROMO_APPLY) }
    val promoMessage: KNode = child { hasTestTag(CartTags.PROMO_MESSAGE) }
    val emptyCartMessage: KNode = child { hasTestTag(CartTags.EMPTY_CART_MESSAGE) }
    val checkoutButton: KNode = child { hasTestTag(CartTags.CHECKOUT_BUTTON) }

    fun cartItem(id: Int): KNode = child { hasTestTag("${CartTags.CART_ITEM}_$id") }
    fun itemPrice(id: Int): KNode = child {hasTestTag("${CartTags.ITEM_PRICE}_$id")}
    fun itemName(id: Int): KNode = child {hasTestTag("${CartTags.ITEM_NAME}_$id")}
    fun itemQuantity(id: Int): KNode = child {hasTestTag("${CartTags.ITEM_QUANTITY}_$id")}
    fun itemMinus(id: Int): KNode = child {hasTestTag("${CartTags.ITEM_MINUS}_$id")}
    fun itemPlus(id: Int): KNode = child {hasTestTag("${CartTags.ITEM_PLUS}_$id")}
    fun itemRemove(id: Int): KNode = child {hasTestTag("${CartTags.ITEM_REMOVE}_$id")}
}