package com.example.checkoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.checkoutdemo.ui.theme.CheckoutDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckoutDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CheckoutDemoApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CheckoutDemoApp(modifier: Modifier = Modifier) {
    var screen by remember { mutableStateOf(Screen.CART) }
    var items by remember { mutableStateOf(initialCartItems) }
    var promoInput by remember { mutableStateOf("") }
    var promoMessage by remember { mutableStateOf<String?>(null) }
    var appliedPercent by remember { mutableStateOf<Int?>(null) }
    var payment by remember { mutableStateOf<PaymentMethod?>(null) }
    var checkoutEnabled by remember { mutableStateOf(initialCartItems.isNotEmpty()) }

    val total = subtotalOf(items).let { it - discountOf(it, appliedPercent) }

    Box(modifier = modifier.fillMaxSize()) {
        when (screen) {
            Screen.CART -> CartScreen(
                items = items,
                promoInput = promoInput,
                promoMessage = promoMessage,
                appliedPercent = appliedPercent,
                checkoutEnabled = checkoutEnabled,
                onPromoInputChange = { promoInput = it },
                onApplyPromo = {
                    val percent = promoCodes[promoInput.trim().uppercase()]
                    if (percent != null) {
                        appliedPercent = percent
                        promoMessage = "Промокод применён: −$percent%"
                    } else {
                        promoMessage = "Промокод недействителен"
                    }
                },
                onIncrease = { id ->
                    items = items.map {
                        if (it.id == id && it.quantity < MAX_QUANTITY) it.copy(quantity = it.quantity + 1) else it
                    }
                    checkoutEnabled = items.isNotEmpty()
                },
                onDecrease = { id ->
                    items = items.map {
                        if (it.id == id && it.quantity > 0) it.copy(quantity = it.quantity - 1) else it
                    }
                    checkoutEnabled = items.isNotEmpty()
                },
                onRemove = { id ->
                    items = items.filterNot { it.id == id }
                },
                onCheckout = { screen = Screen.CHECKOUT },
            )

            Screen.CHECKOUT -> CheckoutScreen(
                items = items,
                appliedPercent = appliedPercent,
                selectedPayment = payment,
                onSelectPayment = { payment = it },
                onPay = { screen = Screen.SUCCESS },
                onBack = { screen = Screen.CART },
            )

            Screen.SUCCESS -> SuccessScreen(
                paymentMethod = payment ?: PaymentMethod.CARD,
                totalKopecks = total,
                onNewOrder = {
                    items = initialCartItems
                    promoInput = ""
                    promoMessage = null
                    appliedPercent = null
                    payment = null
                    checkoutEnabled = initialCartItems.isNotEmpty()
                    screen = Screen.CART
                },
            )
        }
    }
}
