package yeeun


data class Number(
    var number: StringBuilder = StringBuilder(),
    var next: Number? = null,
    var prev: Number? = null
)

val set = mutableSetOf<String>()

fun main() {
    val reader = System.`in`.bufferedReader()
    val target = reader.readLine().toInt().toString()
    val list = mutableListOf<Number>()
    var prev: Number? = null
    for (charNum in target.toCharArray()) {
        val current = Number()
        current.number.append(charNum)
        if (prev != null) {
            current.prev = prev
            prev.next = current
        }
        list.add(current)
        prev = current
    }

    for (start in list) {
        countMethodToMakeNumber(start.copy())
    }

//    print(set.toString())
    print(set.size)
}

fun countMethodToMakeNumber(number: Number) {
    if (number.prev == null && number.next == null) {
        set.add(number.number.toString())
        return
    }

    var temp: Number?
    if (number.prev != null) {
        temp = number.prev!!
        val curr = temp.number.toString()+number.number.toString()
        number.number.append(curr)
        number.prev = temp.prev

        countMethodToMakeNumber(number)

        number.number.setLength(number.number.length - curr.length)
        number.prev = temp
    }

    if (number.next != null) {
        temp = number.next!!
        val curr = number.number.toString()+temp.number.toString()
        number.number.append(curr)
        number.next = temp.next

        countMethodToMakeNumber(number)

        number.number.setLength(number.number.length - curr.length)
        number.next = temp
    }
}