# checkoutDemo

Учебное Android-приложение на Jetpack Compose. Служит объектом для UI-автотестов
на Kaspresso + Kakao Compose.

## Стек

- AGP 9.2.1, Kotlin 2.2.10, Compose BOM 2026.02.01
- minSdk 24, targetSdk 36, namespace `com.example.checkoutdemo`
- Material3, одна `Activity`, без сети, БД и DI — данные захардкожены
- Kaspresso 1.6.0 + `kaspresso-compose-support` в `androidTestImplementation`

## Структура

| Файл | Назначение |
|---|---|
| `CartModel.kt` | модель позиции, стартовый набор, промокоды, форматирование денег |
| `CartScreen.kt` | экран «Корзина» |
| `CheckoutScreen.kt` | экран «Оформление» |
| `SuccessScreen.kt` | экран «Успех» |
| `MainActivity.kt` | состояние приложения и переключение экранов |

Цены хранятся в копейках целым числом, на экране выводятся рублями с двумя
знаками: `49900` → `499,00 ₽`.

## Экраны

### Корзина (`cart_screen`)

Список позиций, у каждой — название, цена за штуку, количество, кнопки `−`, `+`
и «Удалить». Плюс ограничен сверху значением 99. Когда позиций не осталось,
показывается «Корзина пуста» (`empty_cart_message`).

Промокод вводится в поле `promo_input` и применяется кнопкой `promo_apply`
(disabled на пустой строке). Поддерживаются `SALE10` и `SALE20`, регистр не
важен, пробелы по краям обрезаются. Результат выводится в `promo_message`.
Одновременно действует не более одного промокода.

Итоги: `cart_subtotal` (всегда), `cart_discount` (при применённом промокоде),
`cart_total` (всегда). Скидка считается от суммы позиций и округляется вниз до
копейки. Кнопка «К оформлению» — `checkout_button`.

### Оформление (`checkout_screen`)

Три способа оплаты: `payment_card`, `payment_sbp`, `payment_cash`. Изначально не
выбран ни один. Оплата при получении недоступна для дорогих заказов — тогда
рядом появляется пояснение `payment_cash_hint`. Итог — `checkout_total`,
кнопки — `pay_button` (disabled без выбранного способа) и `back_button`.

Возврат назад сохраняет состояние корзины: количества, промокод, итог.

### Успех (`success_screen`)

`success_message`, `success_payment_method`, `success_total` и кнопка
`new_order_button`, которая возвращает на корзину и сбрасывает состояние к
начальному.

## Тестовые теги

Каждый интерактивный и проверяемый элемент помечен `Modifier.testTag(...)`.
Теги позиций параметризованы идентификатором: `cart_item_{id}`,
`item_name_{id}`, `item_price_{id}`, `item_quantity_{id}`, `item_minus_{id}`,
`item_plus_{id}`, `item_remove_{id}`.

## Сборка

```
./gradlew assembleDebug
```

Тесты в репозитории не поставляются — их пишет заказчик.
