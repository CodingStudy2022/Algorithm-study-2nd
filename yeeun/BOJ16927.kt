package yeeun

import java.util.*

class Queue {
    private val list: MutableList<Int> = mutableListOf()

    fun push(value: Int) = list.add(value)
    fun poll(): Int = list.removeAt(0)
    fun getSize() = list.size
    fun print() {
        print("$list\n")
    }
}

internal val arr_: MutableList<MutableList<Pair<Int, Boolean>>> = mutableListOf()
var width: Int = -1
var height: Int = -1
fun main() {
    val reader = System.`in`.bufferedReader()
    var tokenizer = StringTokenizer(reader.readLine())
    height = tokenizer.nextToken().toInt()
    width = tokenizer.nextToken().toInt()
    val count = tokenizer.nextToken().toInt()

    repeat(height) { r ->
        tokenizer = StringTokenizer(reader.readLine())
        arr_.add(mutableListOf())
        repeat(width) {
            arr_[r].add(Pair(tokenizer.nextToken().toInt(), false))
        }
    }

    rotate(arr_, count, 0, width - 1, 0, height - 1)
    printArr()
}

fun printArr() {
    val builder = StringBuilder()
    repeat(height) { r ->
        repeat(width) { c ->
            builder.append(arr_[r][c].first).append(" ")
        }
        builder.append("\n")
    }
    builder.setLength(builder.length - 1)
    print(builder.toString())
}

val modes = listOf(
    Pair(1, 0),
    Pair(0, 1),
    Pair(-1, 0),
    Pair(0, -1),
    Pair(1, 0),
    Pair(0, 1),
    Pair(-1, 0),
    Pair(0, -1),
)

fun rotate(
    arr: MutableList<MutableList<Pair<Int, Boolean>>>,
    count: Int,
    minCol: Int,
    maxCol: Int,
    minRow: Int,
    maxRow: Int
) {
    if (minCol > maxCol || minRow > maxRow) {
        return
    }
    val queue = Queue()
    var row: Int = minRow
    var col: Int = minCol
    val rotate =
        count % (((maxCol - minCol + 1) * (maxRow - minRow + 1)) - ((maxCol - minCol - 1) * (maxRow - minRow - 1)))
    queue.push(arr[row][col].first)
    for (mode in modes) {
        while (
            ((row + mode.first == minRow || row + mode.first == maxRow) && (col + mode.second in minCol..maxCol))
            ||
            ((col + mode.second == minCol || col + mode.second == maxCol) && row + mode.first in minRow..maxRow)
        ) {
            row += mode.first
            col += mode.second
            if (arr[row][col].second) break

            queue.push(arr[row][col].first)
            if (queue.getSize() == rotate + 1) {
                arr[row][col] = Pair(queue.poll(), true)
            }
        }
    }
    rotate(arr, count, minCol + 1, maxCol - 1, minRow + 1, maxRow - 1)
}