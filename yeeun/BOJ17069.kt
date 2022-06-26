package yeeun

import java.util.*

class Space(
    val status: String,
    var up: Space? = null,
    var left: Space? = null,
    var down: Space? = null,
    var right: Space? = null,
    var rightDown: Space? = null,
)

private const val EMPTY_SPACE = "0"

private const val MODE_VERTICAL = 2
private const val MODE_HORIZONTAL = 0
private const val MODE_DIAGONAL = 1
private lateinit var arr: Array<Array<Space>?>

private lateinit var dpMap: Array<Array<Array<Long?>>>

fun main() {
    val reader = System.`in`.bufferedReader()
    var tokenizer = StringTokenizer(reader.readLine())
    val size = tokenizer.nextToken().toInt()

    arr = arrayOfNulls(size)
    var prev = Space("")
    dpMap = Array(size) { n ->
        tokenizer = StringTokenizer(reader.readLine())
        arr[n] = Array(size) { m ->
            val sp = Space(tokenizer.nextToken())
            if (n > 0) {
                arr[n - 1]!![m].down = sp
                sp.up = arr[n - 1]!![m]
            }
            if (m > 0) {
                prev.right = sp
                sp.left = prev
            }
            if (n > 0 && m > 0) arr[n - 1]!![m - 1].rightDown = sp

            prev = sp
            sp
        }
        Array(size) {
            arrayOfNulls(3)
        }
    }

    print(move(0, 1, MODE_HORIZONTAL))
}

fun move(row: Int, col: Int, mode: Int): Long {
    //check posibility
    if (row >= arr.size || col >= arr[row]!!.size) return 0
    if (dpMap[row][col][mode] != null) return dpMap[row][col][mode]!!

    val currentSpace = arr[row]!![col]
    var countSum: Long = 0

    when (mode) {
        MODE_DIAGONAL -> {
            if (currentSpace.status != EMPTY_SPACE) return 0
            if (currentSpace.up?.status != EMPTY_SPACE) return 0
            if (currentSpace.left?.status != EMPTY_SPACE) return 0
            if (row == arr.size - 1 && col == arr[row]!!.size - 1) return 1
            countSum += move(row + 1, col, MODE_VERTICAL)
            countSum += move(row, col + 1, MODE_HORIZONTAL)
            countSum += move(row + 1, col + 1, MODE_DIAGONAL)
        }
        MODE_VERTICAL -> {
            if (currentSpace.status != EMPTY_SPACE) return 0
            if (row == arr.size - 1 && col == arr[row]!!.size - 1) return 1
            countSum += move(row + 1, col, MODE_VERTICAL)
            countSum += move(row + 1, col + 1, MODE_DIAGONAL)
        }
        MODE_HORIZONTAL -> {
            if (currentSpace.status != EMPTY_SPACE) return 0
            if (row == arr.size - 1 && col == arr[row]!!.size - 1) return 1
            countSum += move(row, col + 1, MODE_HORIZONTAL)
            countSum += move(row + 1, col + 1, MODE_DIAGONAL)
        }
    }

    dpMap[row][col][mode] = countSum
    return countSum
}
