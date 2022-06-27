package yeeun

import java.util.*

fun main() {
    val reader = System.`in`.bufferedReader()
    var tokenizer = StringTokenizer(reader.readLine())

    val people = tokenizer.nextToken().toInt()
    val chicken = tokenizer.nextToken().toInt()

    val list: MutableList<MutableList<Int>> = mutableListOf()

    repeat(people) { p ->
        tokenizer = StringTokenizer(reader.readLine())
        list.add(mutableListOf())
        repeat(chicken) { c ->
            list[p].add(tokenizer.nextToken().toInt())
        }
    }

    val arr = Array(people) { 0 }
    print(pickChicken(list, 0, 3, arr))
}

fun pickChicken(list: MutableList<MutableList<Int>>, index: Int, pick: Int, arr: Array<Int>): Int {
    if (index >= list[0].size || pick == 0) {
        var sum = 0
        for (value in arr) {
            sum += value
        }
        return sum
    }

    val copy = arr.clone()
    for (people in list.indices) {
        arr[people] = Math.max(list[people][index], arr[people])
    }

    return Math.max(
        pickChicken(list, index + 1, pick - 1, arr),
        pickChicken(list, index + 1, pick, copy)
    )
}