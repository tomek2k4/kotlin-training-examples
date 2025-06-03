package org.example.algorytmy.substring

fun main() {
    /*
    Find all starting indexes in string "k" that contains anagrams of string "o"

    Example 1:
    Input:  s = "abacab", t = "ab"
    Output: [0,1,4]

    Example 2:
    Input: s = "abacab", t = "abc",
    Output: [1,3]
    */
    println("Hello World!")

    println(findAllContainsSlidingWindow("abacab", "abc").toList())
}

fun findAllContainsSlidingWindow(s: String, t: String): Array<Int> {
    if (t.length > s.length) return emptyArray()
    val outputIndices = mutableListOf<Int>()
    var begin = 0
    var end = 0
    val targetLettersMap = mutableMapOf<Char, Int>()
    t.forEach {
        targetLettersMap[it] = targetLettersMap.getOrDefault(it, 0) + 1
    }
    var counter = targetLettersMap.size

    while (end < s.length) {
        val c: Char = s[end]
        if (targetLettersMap.contains(c)) {
            targetLettersMap[c] = targetLettersMap[c]!! - 1
            if (targetLettersMap[c]!! == 0) {
                counter--
            }
        }
        end++

        while (counter == 0) {
            val tempC = s[begin]
            if (targetLettersMap.contains(tempC)) {
                targetLettersMap[tempC] = targetLettersMap[tempC]!! + 1
                if (targetLettersMap[tempC]!! > 0) {
                    counter++
                }
            }

            if (end - begin == t.length) {
                outputIndices.add(begin)
            }
            begin++
        }
    }
    return outputIndices.toTypedArray()
}

