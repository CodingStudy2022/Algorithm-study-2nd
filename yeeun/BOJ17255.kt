package yeeun


class Number {
    var number: StringBuilder = StringBuilder()
    var next: Number? = null
    var prev: Number? = null

    fun append(string: String) {
        number.append(string)
    }

    fun remove(len: Int) {
        number.setLength(number.length - len)
    }

    fun copy(): Number {
        val copy = Number()
        copy.number = StringBuilder().append(number.toString())
        copy.next = next
        copy.prev = prev
        return copy
    }


}

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
        countMethodToMakeNumber(start.copy(), start.number.toString())
    }

    println(set.toString())
    print(set.size)
}

fun countMethodToMakeNumber(number: Number, prevNumber: String) {
    if (number.prev == null && number.next == null) {
        set.add(number.number.toString())
        return
    }

    var temp: Number?
    if (number.prev != null) {
        temp = number.prev!!
        val curNumber = temp.number.toString() + prevNumber
        number.prev = temp.prev

        number.append("->")
        number.append(curNumber)
        countMethodToMakeNumber(number, curNumber)
        number.remove(curNumber.length+2)

        number.prev = temp
    }

    if (number.next != null) {
        temp = number.next!!
        val curNumber = prevNumber + temp.number.toString()
        number.next = temp.next

        number.append("->")
        number.append(curNumber)
        countMethodToMakeNumber(number, curNumber)
        number.remove(curNumber.length+2)

        number.next = temp
    }
}