package org.example

fun main() {

    /*
    Example 1:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]

    Example 2:
    Input: nums = [3,2,4], target = 6
    Output: [1,2]

    Example 3:
    Input: nums = [3,3], target = 6
    Output: [0,1]
    */


    val integersArray = intArrayOf(3,2,4)
    val target = 6

    println(twoSumsMap(integersArray, target))
}


fun twoSumsBrute(inputArray: IntArray, target: Int): List<Int> {
    if (inputArray.isEmpty()) return emptyList()
    inputArray.forEachIndexed { firstIndex, first ->
        print("$firstIndex  ")
        inputArray.forEachIndexed innerLoop@{ secondIndex, second ->
            if (firstIndex == secondIndex) return@innerLoop
            print("$secondIndex ")
            if (first + second - target == 0) {
                return listOf(firstIndex, secondIndex)
            }
        }
        println()
    }
    return emptyList()
}

fun twoSumsBruteOptimized(numbs: IntArray, target: Int): List<Int> {
    if (numbs.isEmpty()) return emptyList()
    for ((firstIndex, first) in numbs.withIndex()) {
        print("$firstIndex  ")
        val nextIndex = firstIndex + 1
        for ((remainingIndex, second) in numbs.takeLast(numbs.size - nextIndex).withIndex()) {
            val secondIndex = remainingIndex + nextIndex
            print("$secondIndex ")
            if (first + second - target == 0) {
                return listOf(firstIndex, secondIndex)
            }
        }
        println()
    }
    return emptyList()
}

fun twoSumsMap(numbs: IntArray, target: Int): List<Int> {
    val mapNumbs = hashMapOf<Int, Int>()
    for ((index, element) in numbs.withIndex()) {
        val lookingFor = target - element
        if (mapNumbs.contains(lookingFor)) {
            return arrayListOf(index, mapNumbs.get(lookingFor)!!)
        }
        mapNumbs.put(element, index)
    }
    return emptyList()
}
