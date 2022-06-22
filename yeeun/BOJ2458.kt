package yeeun

import java.util.*

data class Student(
    val num: Int,
    val tallerStudents: MutableList<Student> = mutableListOf(),
    val smallerStudents: MutableList<Student> = mutableListOf(),
    var visitedSet: Set<Int>? = null,
    var smallerCount: Set<Int>? = null
)

fun main() {
    val reader = System.`in`.bufferedReader()
    var tokenizer = StringTokenizer(reader.readLine())
    val studentCount = tokenizer.nextToken().toInt()
    val compareCount = tokenizer.nextToken().toInt()

    val studentList = mutableListOf<Student>()
    for (i in 0..studentCount) {
        studentList.add(Student(i))
    }

    for (i in 0 until compareCount) {
        tokenizer = StringTokenizer(reader.readLine())
        val smaller = studentList[tokenizer.nextToken().toInt()]
        val taller = studentList[tokenizer.nextToken().toInt()]
        smaller.tallerStudents.add(taller)
        taller.smallerStudents.add(smaller)
    }

    var count = 0
    for (i in 1..studentCount) {
        if (checkKnowRank(studentList[i], studentCount)) count++
    }
    print(count)
}

fun checkKnowRank(student: Student, studentCount: Int): Boolean {
    val taller = getTallerStudents(student)
    val smaller = getSmallerStudents(student)
    return taller.size + smaller.size + 1 == studentCount
}

fun getTallerStudents(student: Student): Set<Int> {
    if (student.visitedSet != null) {
        return student.visitedSet!!
    }
    val tallerSet = mutableSetOf<Int>()
    for (taller in student.tallerStudents) {
        tallerSet.add(taller.num)
        tallerSet.addAll(getTallerStudents(taller).toList())
    }
    student.visitedSet = tallerSet
    return tallerSet
}

fun getSmallerStudents(student: Student): Set<Int> {
    if (student.smallerCount != null) {
        return student.smallerCount!!
    }
    val smallerSet = mutableSetOf<Int>()
    for (smaller in student.smallerStudents) {
        smallerSet.add(smaller.num)
        smallerSet.addAll(getSmallerStudents(smaller).toList())
    }
    student.smallerCount = smallerSet
    return smallerSet
}