package yeeun

import java.util.*

data class Order(
    val burger: Int,
    val fries: Int
)

lateinit var orders : Array<Order>
val dp = Array(101){
    Array(301){
        Array(301){
            -1
        }
    }
}
fun main() {
    val reader = System.`in`.bufferedReader()
    var tokenizer = StringTokenizer(reader.readLine())
    val orderCount = tokenizer.nextToken().toInt()
    val burgerCount = tokenizer.nextToken().toInt()
    val friesCount = tokenizer.nextToken().toInt()

    orders = Array(orderCount){
        tokenizer = StringTokenizer(reader.readLine())
        val burger = tokenizer.nextToken().toInt()
        val fries = tokenizer.nextToken().toInt()
        Order(burger, fries)
    }

    print(knapsack(0, burgerCount, friesCount))
}


fun knapsack(targetIndex: Int, burgerCount: Int, friesCount: Int): Int {
    if (targetIndex >= orders.size) return 0

    val memorizedCount = dp[targetIndex][burgerCount][friesCount]
    if(memorizedCount!=-1) return memorizedCount


    val target = orders[targetIndex]

    var count = knapsack(targetIndex + 1, burgerCount, friesCount)
    val remainBurgerCount = burgerCount - target.burger
    val remainFriesCount = friesCount - target.fries
    if (remainBurgerCount >= 0 && remainFriesCount >= 0)
        count = Math.max(count, knapsack(targetIndex + 1, remainBurgerCount, remainFriesCount) + 1)

    dp[targetIndex][burgerCount][friesCount]=count
    return count
}
