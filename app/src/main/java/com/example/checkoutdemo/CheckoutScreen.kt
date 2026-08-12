package com.example.checkoutdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun CheckoutScreen(
    items: List<CartItem>,
    appliedPercent: Int?,
    selectedPayment: PaymentMethod?,
    onSelectPayment: (PaymentMethod) -> Unit,
    onPay: () -> Unit,
    onBack: () -> Unit,
) {
    val subtotal = subtotalOf(items)
    val cashAvailable = subtotal <= CASH_PAYMENT_LIMIT_KOPECKS

    Column(
        modifier = Modifier
            .testTag("checkout_screen")
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Оформление",
            style = MaterialTheme.typography.headlineMedium,
        )

        PaymentOption(
            method = PaymentMethod.CARD,
            tag = "payment_card",
            selected = selectedPayment == PaymentMethod.CARD,
            enabled = true,
            onSelect = { onSelectPayment(PaymentMethod.CARD) },
        )
        PaymentOption(
            method = PaymentMethod.SBP,
            tag = "payment_sbp",
            selected = selectedPayment == PaymentMethod.SBP,
            enabled = true,
            onSelect = { onSelectPayment(PaymentMethod.SBP) },
        )
        PaymentOption(
            method = PaymentMethod.CASH,
            tag = "payment_cash",
            selected = selectedPayment == PaymentMethod.CASH,
            enabled = cashAvailable,
            onSelect = { onSelectPayment(PaymentMethod.CASH) },
        )
        if (!cashAvailable) {
            Text(
                text = "Недоступно для заказов дороже ${formatMoney(CASH_PAYMENT_LIMIT_KOPECKS)}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.testTag("payment_cash_hint"),
            )
        }

        Text(
            text = "Итого: ${formatMoney(subtotal)}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.testTag("checkout_total"),
        )

        Button(
            onClick = onPay,
            enabled = selectedPayment != null,
            modifier = Modifier
                .testTag("pay_button")
                .fillMaxWidth(),
        ) {
            Text("Оплатить")
        }
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier
                .testTag("back_button")
                .fillMaxWidth(),
        ) {
            Text("Назад")
        }
    }
}

@Composable
private fun PaymentOption(
    method: PaymentMethod,
    tag: String,
    selected: Boolean,
    enabled: Boolean,
    onSelect: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
            enabled = enabled,
            modifier = Modifier.testTag(tag),
        )
        Text(text = method.title)
    }
}
