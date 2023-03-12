package ru.netology

fun main() {

    // задаем ограничения для типов платежных систем
    val cardLimitPerDay = 150_000.0
    val cardLimitPerMonth = 600_000.0
    val vkPayLimitPerTransaction = 15_000.0
    val vkPayLimitPerMonth = 40_000.0

    fun finalCommission(paymentType: String = "VK Pay", totalThisMonth: Double, transactionSum: Double): Double {

        var calculation = 0.0
        if (totalThisMonth < cardLimitPerMonth && transactionSum <= cardLimitPerDay) {
            calculation = when (paymentType) {
                "Mastercard" -> if (transactionSum < 75_000) 0.0 else 0.006 * transactionSum + 20.0
                "Maestro" -> if (transactionSum < 75_000) 0.0 else 0.006 * transactionSum + 20.0
                "Visa" -> if (transactionSum * 0.0075 < 35.0) 35.0 else 0.0075 * transactionSum
                "Мир" -> if (transactionSum * 0.0075 < 35.0) 35.0 else 0.0075 * transactionSum
                // всю голову сломал о том, что должно произойти с переменной calculation  в случае нарушения лимитов по ВК. -99999 - условность
                "VK Pay" -> if (transactionSum <= vkPayLimitPerTransaction && vkPayLimitPerMonth <= totalThisMonth) 0.0 else -99999.0
                else -> 0.0
            }
        } else {
            println("Вы превысили лимит на переводы")
        }
        return calculation
    }
    println(finalCommission("VK Pay", 40_000.0, 16_000.0))
}
