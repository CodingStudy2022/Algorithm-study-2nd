package yeeun

import java.util.*

var firstColor: Int = -1
lateinit var houseCostMap: Array<Array<Int>>
lateinit var memorizationMap: Array<Array<Int>>

fun main() {
    val reader = System.`in`.bufferedReader()
    val houseCount = reader.readLine().toInt()

    houseCostMap = Array(houseCount) { i ->
        val tokenizer = StringTokenizer(reader.readLine())
        arrayOf(
            tokenizer.nextToken().toInt(),
            tokenizer.nextToken().toInt(),
            tokenizer.nextToken().toInt(),
        )
    }

    var minCost = Integer.MAX_VALUE
    repeat(3) {
        firstColor = it
        memorizationMap = Array(houseCount) { arrayOf(-1, -1, -1) }
        val cost = paintHouse(0, 0, it, houseCount)
        minCost = Math.min(cost, minCost)
    }
    print(minCost)
}

fun paintHouse(total: Int, houseNum: Int, colorNum: Int, houseCount: Int): Int {
    val color = colorNum % 3
    val house = houseNum % houseCount
    val add = total + houseCostMap[house][color]!!

    if (memorizationMap[house][color] != -1) {
        return memorizationMap[house][color] + total
    }

    if (house == houseCount - 1) {
        return if (firstColor != color) add
        else Integer.MAX_VALUE // last house color is same as first house color
    }

    val nextColor = paintHouse(add, house + 1, color + 1, houseCount)
    val nextNextColor = paintHouse(add, house + 1, color + 2, houseCount)

    val minSum = Math.min(nextColor, nextNextColor)
    memorizationMap[house][color] = minSum - total
    return minSum
}