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
private const val MODE_HORIZONTAL   = 0
private const val MODE_DIAGONAL     = 1
private const val MODE_VERTICAL     = 2

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
    // out.
    if (row >= arr.size || col >= arr[row]!!.size) return 0
    val currentSpace = arr[row]!![col]

    // blocked
    if(isBlockedSpace(currentSpace,mode)) return 0

    // reached destiny
    if (row == arr.size - 1 && col == arr[row]!!.size - 1) return 1

    // use memorized value
    if (dpMap[row][col][mode] != null) return dpMap[row][col][mode]!!

    // sum values
    var countSum: Long = 0
    when (mode) {
        MODE_DIAGONAL -> {
            countSum += moveHorizontal(row, col)
            countSum += moveVertical(row, col)
        }
        MODE_VERTICAL -> {
            countSum += moveVertical(row, col)
        }
        MODE_HORIZONTAL -> {
            countSum += moveHorizontal(row, col)
        }
    }
    countSum += moveDiagonal(row, col)

    // memorize and return
    dpMap[row][col][mode] = countSum
    return countSum
}

fun isBlockedSpace(space: Space, mode: Int): Boolean {
    return when (mode) {
        MODE_DIAGONAL ->
            space.status != EMPTY_SPACE || space.up?.status != EMPTY_SPACE || space.left?.status != EMPTY_SPACE
        MODE_VERTICAL ->
            space.status != EMPTY_SPACE
        MODE_HORIZONTAL ->
            space.status != EMPTY_SPACE
        else -> true
    }
}

fun moveHorizontal(row: Int, col: Int) = move(row, col + 1, MODE_HORIZONTAL)
fun moveVertical(row: Int, col: Int) = move(row + 1, col, MODE_VERTICAL)
fun moveDiagonal(row: Int, col: Int) = move(row + 1, col + 1, MODE_DIAGONAL)
