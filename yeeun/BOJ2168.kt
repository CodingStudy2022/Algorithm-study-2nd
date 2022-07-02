package yeeun.temp

import java.util.*

fun main() {
    val tokenizer = StringTokenizer(System.`in`.bufferedReader().readLine())
    val width = tokenizer.nextToken().toInt()
    val height = tokenizer.nextToken().toInt()
    print(countDiagonal(width, height))
}

fun countDiagonal(width: Int, height: Int): Int {
    val gcd = getGCD(Math.max(width, height), Math.min(width, height))
    return if (gcd == 1) {
        width + height - 1
    } else {
        gcd * countDiagonal(width / gcd, height / gcd)
    }
}

fun getGCD(bigger: Int, smaller: Int): Int {
    var a = bigger
    var b = smaller
    while (b != 0) {
        val n = a % b
        a = b
        b = n
    }
    return a
}