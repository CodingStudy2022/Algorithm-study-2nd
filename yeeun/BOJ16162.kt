package yeeun

import java.util.*

lateinit var soundArray: Array<Int>
var commonDifference: Int = -1

fun main() {
    val reader = System.`in`.bufferedReader()
    var tokenizer = StringTokenizer(reader.readLine())

    val soundCount = tokenizer.nextToken().toInt()
    val firstSound = tokenizer.nextToken().toInt()
    commonDifference = tokenizer.nextToken().toInt()

    tokenizer = StringTokenizer(reader.readLine())
    soundArray = Array<Int>(soundCount) {
        tokenizer.nextToken().toInt()
    }
    print(findSoundFrom(0, firstSound))
}

fun findSoundFrom(index: Int, targetSound: Int): Int {
    if (index >= soundArray.size) {
        return 0
    }

    for (i in index until soundArray.size) {
        if (soundArray[i] == targetSound) {
            return 1 + findSoundFrom(i + 1, targetSound + commonDifference)
        }
    }
    return 0
}