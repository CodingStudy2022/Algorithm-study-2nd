package yeeun

import java.util.*

// snakeDirections[n-1] = turn left, snakeDirections[n+1] = turn right
val snakeDirections = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
val snake = Snake()
val controls = mutableListOf<Pair<Int, String>>()
lateinit var appleMap: Array<Array<Boolean>>

fun main() {
    val reader = System.`in`.bufferedReader()
    val size = reader.readLine().toInt()
    val appleCount = reader.readLine().toInt()
    appleMap = Array(size) { Array(size) { false } }

    appleMap[0][0] = true
    repeat(appleCount) {
        val tokenizer = StringTokenizer(reader.readLine())
        val row = tokenizer.nextToken().toInt() - 1
        val col = tokenizer.nextToken().toInt() - 1
        appleMap[row][col] = true
    }


    val directionCount = reader.readLine().toInt()
    repeat(directionCount) {
        val tokenizer = StringTokenizer(reader.readLine())
        val time = tokenizer.nextToken().toInt()
        val direction = tokenizer.nextToken()
        controls.add(Pair(time, direction))
    }

    print(moveSnake())
}

fun moveSnake(): Int {
    var directionNum = 0
    var rowNum = 0
    var colNum = 0
    var timeCount = 0

    while (
        rowNum >= 0 && rowNum < appleMap.size
        && colNum >= 0 && colNum < appleMap[0].size
        && !snake.contains(rowNum, colNum)
    ) {
        // move snake
        snake.moveTo(Pair(rowNum, colNum))
        if (!appleMap[rowNum][colNum]) snake.removeTail()
        else appleMap[rowNum][colNum]=false


        // control change
        if (controls.isNotEmpty() && controls[0].first == timeCount) {
            val control = controls.removeAt(0)
            if (control.second == "D") {
                directionNum++
            } else if (control.second == "L") {
                directionNum += (snakeDirections.size - 1)
            }
        }
        //snake.print(timeCount)

        // next time and direction
        directionNum %= snakeDirections.size
        val direction = snakeDirections[directionNum]
        rowNum += direction.first
        colNum += direction.second
        timeCount++
    }

    return timeCount
}


class Snake {
    private val list = mutableListOf<Pair<Int, Int>>()

    fun contains(row: Int, col: Int): Boolean {
        //println("${list}\n check = ($row, $col)")
        for (pair in list) {
            if (pair.first == row && pair.second == col) return true
        }
        return false
    }

    fun print(timeCount: Int) {
        println("time($timeCount), $list")

        for (row in appleMap.indices) {
            for (col in appleMap[row].indices) {
                if (this.contains(row, col)) print("■")
                else if (appleMap[row][col]) print("▣")
                else print("□")
            }
            println()
        }
    }

    fun moveTo(pair: Pair<Int, Int>) = list.add(pair)
    fun removeTail() = list.removeAt(0)
}

