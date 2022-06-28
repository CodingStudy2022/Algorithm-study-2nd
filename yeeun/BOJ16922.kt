package yeeun

val numbers = arrayOf(1, 5, 10, 50)
val sets = mutableSetOf<Int>()
fun main() {
    val count = System.`in`.bufferedReader().readLine().toInt()

    fill(0, count, 0)
    print(sets.size)
}

fun fill(index: Int, left: Int, sum: Int){
    if (left == 0) {
        sets.add(sum)
        return
    }
    if (index == numbers.size - 1) {
        sets.add((numbers[index] * left) + sum)
        return
    }

    for (i in 0..left) {
        val added = (numbers[index] * (left - i)) + sum
        fill(index + 1, i, added)
    }
}

