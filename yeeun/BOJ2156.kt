package yeeun.temp

fun main() {
    val reader = System.`in`.bufferedReader()
    val glassCount = reader.readLine().toInt()

    val glassList = mutableListOf<Int>()
    repeat(glassCount) {
        glassList.add(reader.readLine().toInt())
    }
    val dp = Array(glassCount) { Array(4) { -1 } }

    print(drinkOrNot(dp, glassList, 0, 3, 0))
}

fun drinkOrNot(
    dp: Array<Array<Int>>,
    list: MutableList<Int>,
    index: Int,
    limit: Int,
    sum: Int
): Int {
    if (index == list.size) return sum

    val prevResult = dp[index][limit]
    if (prevResult != -1) return sum + prevResult

    val selected = list[index]
    println("${selected}를 마실 까 말까? 지금까지 $sum")
    var max = -1
    if (limit > 1) {
        val drink = drinkOrNot(dp, list, index + 1, limit - 1, selected)
        println("${selected}를 마셨을 때의 최대 값, $drink")
        max = Math.max(drink, max)
    }

    val not = drinkOrNot(dp, list, index + 1, 3, 0)
    println("${selected}를 안 마셨을 때의 최대 값, $not")
    max = Math.max(not, max)

    dp[index][limit] = max
    println()
    return sum + max
}
