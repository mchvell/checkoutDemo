package com.example.checkoutdemo

/** Позиция корзины. Цена хранится в копейках. */
data class CartItem(
    val id: Int,
    val name: String,
    val priceKopecks: Int,
    val quantity: Int,
)

/** Стартовый набор корзины. */
val initialCartItems: List<CartItem> = listOf(
    CartItem(id = 1, name = "Кофе молотый", priceKopecks = 49900, quantity = 1),
    CartItem(id = 2, name = "Чайник", priceKopecks = 299900, quantity = 1),
    CartItem(id = 3, name = "Кружка", priceKopecks = 89900, quantity = 2),
)

const val MAX_QUANTITY = 99

/** Порог, выше которого оплата при получении недоступна. */
const val CASH_PAYMENT_LIMIT_KOPECKS = 500000

enum class Screen { CART, CHECKOUT, SUCCESS }

enum class PaymentMethod(val title: String) {
    CARD("Картой"),
    SBP("СБП"),
    CASH("При получении"),
}

/** Промокоды: код -> размер скидки в процентах. */
val promoCodes: Map<String, Int> = mapOf(
    "SALE10" to 10,
    "SALE20" to 20,
)

/** 49900 -> "499,00 ₽" */
fun formatMoney(kopecks: Int): String {
    val sign = if (kopecks < 0) "−" else ""
    val abs = kotlin.math.abs(kopecks)
    return "$sign${abs / 100},${(abs % 100).toString().padStart(2, '0')} ₽"
}

fun subtotalOf(items: List<CartItem>): Int = items.sumOf { it.priceKopecks * it.quantity }

/** Скидка от суммы позиций, округление вниз до копейки. */
fun discountOf(subtotal: Int, percent: Int?): Int =
    if (percent == null) 0 else subtotal * percent / 100
