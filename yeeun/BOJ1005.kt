package yeeun

import java.util.*

data class Building(
    val num: Int,
    val time: Int,
    val prev: MutableList<Building> = mutableListOf(),
    var prevTime: Int = -1
)

fun main() {
    val reader = System.`in`.bufferedReader()
    val caseCount = reader.readLine().toInt()
    val builder = StringBuilder()

    for (i in 0 until caseCount) {
        var tokenizer = StringTokenizer(reader.readLine())
        val buildingCount = tokenizer.nextToken().toInt()
        val ruleCount = tokenizer.nextToken().toInt()

        val buildings = mutableListOf<Building>()
        buildings.add(Building(0, 0))// empty building for index 0

        tokenizer = StringTokenizer(reader.readLine())
        for (num in 1..buildingCount) {
            val time = tokenizer.nextToken().toInt()
            buildings.add(Building(num, time))
        }

        for (i in 0 until ruleCount) {
            tokenizer = StringTokenizer(reader.readLine())
            val firstBuilding = buildings[tokenizer.nextToken().toInt()]
            val secondBuilding = buildings[tokenizer.nextToken().toInt()]
            secondBuilding.prev.add(firstBuilding)
        }
        val targetIndex = reader.readLine().toInt()
        builder.append(calculateBuildingTime(buildings[targetIndex])).append('\n')
    }
    builder.setLength(builder.length - 1)
    print(builder.toString())
}

private fun calculateBuildingTime(building: Building): Int {
    if(building.prevTime!=-1){
        return building.time+building.prevTime
    }
    var maxTime =0
    for (prev in building.prev) {
        val time = calculateBuildingTime(prev)
        if (time > maxTime) {
            maxTime = time
        }
    }
    building.prevTime = maxTime
    return building.time + maxTime
}
