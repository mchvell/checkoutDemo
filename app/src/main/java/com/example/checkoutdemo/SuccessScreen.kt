package com.example.checkoutdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun SuccessScreen(
    paymentMethod: PaymentMethod,
    totalKopecks: Int,
    onNewOrder: () -> Unit,
) {
    Column(
        modifier = Modifier
            .testTag("success_screen")
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Заказ оформлен",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("success_message"),
        )
        Text(
            text = "Способ оплаты: ${paymentMethod.title}",
            modifier = Modifier.testTag("success_payment_method"),
        )
        Text(
            text = "Оплачено: ${formatMoney(totalKopecks)}",
            modifier = Modifier.testTag("success_total"),
        )
        Button(
            onClick = onNewOrder,
            modifier = Modifier
                .testTag("new_order_button")
                .fillMaxWidth(),
        ) {
            Text("Новый заказ")
        }
    }
}
