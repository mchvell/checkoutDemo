package com.example.checkoutdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun CartScreen(
    items: List<CartItem>,
    promoInput: String,
    promoMessage: String?,
    appliedPercent: Int?,
    checkoutEnabled: Boolean,
    onPromoInputChange: (String) -> Unit,
    onApplyPromo: () -> Unit,
    onIncrease: (Int) -> Unit,
    onDecrease: (Int) -> Unit,
    onRemove: (Int) -> Unit,
    onCheckout: () -> Unit,
) {
    val subtotal = subtotalOf(items)
    val discount = discountOf(subtotal, appliedPercent)
    val total = subtotal - discount

    Column(
        modifier = Modifier
            .testTag("cart_screen")
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Корзина",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("cart_title"),
        )

        if (items.isEmpty()) {
            Text(
                text = "Корзина пуста",
                modifier = Modifier.testTag("empty_cart_message"),
            )
        } else {
            items.forEach { item ->
                CartItemRow(
                    item = item,
                    onIncrease = { onIncrease(item.id) },
                    onDecrease = { onDecrease(item.id) },
                    onRemove = { onRemove(item.id) },
                )
                HorizontalDivider()
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = promoInput,
                onValueChange = onPromoInputChange,
                label = { Text("Промокод") },
                singleLine = true,
                modifier = Modifier
                    .testTag("promo_input")
                    .weight(1f),
            )
            Button(
                onClick = onApplyPromo,
                enabled = promoInput.isNotBlank(),
                modifier = Modifier.testTag("promo_apply"),
            ) {
                Text("Применить")
            }
        }

        if (promoMessage != null) {
            Text(
                text = promoMessage,
                modifier = Modifier.testTag("promo_message"),
            )
        }

        Text(
            text = "Сумма: ${formatMoney(subtotal)}",
            modifier = Modifier.testTag("cart_subtotal"),
        )
        if (appliedPercent != null) {
            Text(
                text = "Скидка: ${formatMoney(-discount)}",
                modifier = Modifier.testTag("cart_discount"),
            )
        }
        Text(
            text = "Итого: ${formatMoney(total)}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.testTag("cart_total"),
        )

        Button(
            onClick = onCheckout,
            enabled = checkoutEnabled,
            modifier = Modifier
                .testTag("checkout_button")
                .fillMaxWidth(),
        ) {
            Text("К оформлению")
        }
    }
}

@Composable
private fun CartItemRow(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit,
) {
    Column(
        modifier = Modifier
            .testTag("cart_item_${item.id}")
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.testTag("item_name_${item.id}"),
        )
        Text(
            text = formatMoney(item.priceKopecks),
            modifier = Modifier.testTag("item_price_${item.id}"),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedButton(
                onClick = onDecrease,
                enabled = item.quantity > 0,
                modifier = Modifier.testTag("item_minus_${item.id}"),
            ) {
                Text("−")
            }
            Text(
                text = item.quantity.toString(),
                modifier = Modifier.testTag("item_quantity_${item.id}"),
            )
            OutlinedButton(
                onClick = onIncrease,
                enabled = item.quantity < MAX_QUANTITY,
                modifier = Modifier.testTag("item_plus_${item.id}"),
            ) {
                Text("+")
            }
            OutlinedButton(
                onClick = onRemove,
                modifier = Modifier.testTag("item_remove_${item.id}"),
            ) {
                Text("Удалить")
            }
        }
    }
}
