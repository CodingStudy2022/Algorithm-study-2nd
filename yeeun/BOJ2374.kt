package yeeun

import java.math.BigInteger

fun main() {
    BOJ2374.read()
    BOJ2374.printAddCount()
}

class NumberNode(var num: Int) {
    var prev: NumberNode? = null
    var next: NumberNode? = null
    var isRoot = false

    fun add(): Int {
        val prevNum = prev?.num ?: Integer.MAX_VALUE
        val nextNum = next?.num ?: Integer.MAX_VALUE
        val min = Math.min(prevNum,nextNum)

        if(prevNum==min){
            if (prev!!.isRoot) isRoot = true
            this.prev = prev!!.prev
        }
        if(nextNum==min){
            this.next = next!!.next
        }

        val addedCount = min - num
        num = min
        return addedCount
    }
}


class BOJ2374 {
    companion object {
        var length = -1
        lateinit var root: NumberNode

        fun read() {
            val reader = System.`in`.bufferedReader()
            length = reader.readLine().toInt()

            var prevNum: NumberNode? = null
            for (i in 0 until length) {
                val num = reader.readLine().toInt()
                if (prevNum?.num == num) continue

                val curNum = NumberNode(num)
                curNum.prev = prevNum
                if (prevNum == null) {
                    root = curNum
                    curNum.isRoot = true
                } else if (prevNum.num != num) {
                    prevNum.next = curNum
                }
                prevNum = curNum
            }
        }

        fun printAddCount() {
            var count:BigInteger = BigInteger.valueOf(0)
            while (root.next != null) {
                val node = selectMinNode()
                count += node.add().toBigInteger()
                if (node.isRoot) root = node
            }
            print(count)
        }

        private fun selectMinNode(): NumberNode {
            var min: NumberNode = root
            var node: NumberNode? = root
            while (node != null) {
                if (node.num < min.num) {
                    min = node
                }
                node = node.next
            }
            return min
        }


    }
}