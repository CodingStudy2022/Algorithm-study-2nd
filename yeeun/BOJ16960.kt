package yeeun

import java.util.*

data class Switch(
    val num: Int,
    val connectedLamps: MutableList<Int> = mutableListOf()
)

fun main() {
    val reader = System.`in`.bufferedReader()
    var tokenizer = StringTokenizer(reader.readLine())

    val switchCount = tokenizer.nextToken().toInt()
    val lampCount = tokenizer.nextToken().toInt()

    val switchArray = Array(switchCount) {
        val switch = Switch(it)
        tokenizer = StringTokenizer(reader.readLine())
        val connectedCount = tokenizer.nextToken().toInt()
        repeat(connectedCount) {
            val lampIndex = tokenizer.nextToken().toInt() - 1
            switch.connectedLamps.add(lampIndex)
        }
        switch
    }

    var maxSetSize = 0
    repeat(switchCount) {
        val resultSetSize = pressSwitchWithout(it, switchArray)
        maxSetSize = Math.max(maxSetSize, resultSetSize)
    }

    if (maxSetSize == lampCount) print(1)
    else print(0)
}

fun pressSwitchWithout(index: Int, switchArray: Array<Switch>): Int {
    val set = mutableSetOf<Int>()
    for (i in switchArray.indices) {
        if (i != index)
            set.addAll(switchArray[i].connectedLamps)
    }
    println(set)
    return set.size
}
