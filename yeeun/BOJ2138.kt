package yeeun

import kotlin.math.min

fun main() {
    BOJ2138.read()
    BOJ2138.print()
}

class BOJ2138 {
    companion object {
        private var length = -1
        private lateinit var start: Array<Boolean>
        private lateinit var target: Array<Boolean>

        fun read() {
            val reader = System.`in`.bufferedReader()
            length = reader.readLine().toInt()

            start = Array(length) {
                reader.read() == 49
            }
            reader.read()
            target = Array(length) {
                reader.read() == 49
            }
            reader.read()
            reader.close()
        }

        fun print() {
            pressButton(0)
            var min = countButtonPressed(1, start, 1)
            pressButton(0)

            val result = countButtonPressed(1, start, 0)

            if (min is Int && result is Int) {
                min = min(min, result)
            } else if (result is Int) {
                min = result
            }

            if (min == null) print("-1")
            else print(min)
        }

        private fun countButtonPressed(index: Int, start: Array<Boolean>, prevCount: Int): Int? {
            if (index == start.size) {
                if (isStartSameToTarget()) return prevCount
                else return null
            }
            var count = 0
            if (start[index - 1] != target[index - 1]) {
                count++
                pressButton(index)
            }

            val nextCount = countButtonPressed(index + 1, start, count)
            if (count > 0) pressButton(index)
            return when (nextCount) {
                null -> null
                else -> nextCount + prevCount
            }
        }

        private fun isStartSameToTarget(): Boolean {
            for (i in start.indices)
                if (start[i] != target[i])
                    return false
            return true
        }

        private fun pressButton(index: Int) {
            if (index > 0) start[index - 1] = !start[index - 1]
            if (index < start.size) start[index] = !start[index]
            if (index < start.size - 1) start[index + 1] = !start[index + 1]
        }

    }
}