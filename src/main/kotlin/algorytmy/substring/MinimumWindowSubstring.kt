package org.example.algorytmy.substring

fun main() {
    /*
    Given two strings s and t of lengths m and n respectively, return the minimum window substring
    of s such that every character in t (including duplicates) is included in the window.
    If there is no such substring, return the empty string "".

    Example 1:
    Input: s = "ADOBECODEBANC", t = "ABC"
    Output: "BANC"
    Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

    Example 2:
    Input: s = "a", t = "a"
    Output: "a"
    Explanation: The entire string s is the minimum window.

    Example 3:
    Input: s = "a", t = "aa"
    Output: ""
    Explanation: Both 'a's from t must be included in the window.
    Since the largest window of s only has one 'a', return empty string.
    */
    println("Hello World!")
    println(findMinimumWindowSubstring("ADOBECODEBANC", "ABC"))
}

fun findMinimumWindowSubstring(s: String, t: String): String {
    var output = ""
    if (t.length > s.length) return output
    var begin = 0
    var end = 0

    val lookUpMap = mutableMapOf<Char, Int>()
    t.forEach {
        lookUpMap[it] = lookUpMap.getOrDefault(it, 0) + 1
    }
    var counter = lookUpMap.size
    var subStringLength = Int.MAX_VALUE

    while (end < s.length) {
        var c = s[end]
        if (lookUpMap.contains(c)) {
            lookUpMap[c] = lookUpMap[c]!! - 1
            if (lookUpMap[c]!! == 0) {
                counter--
            }
        }
        end++
        while (counter == 0) {
            var tempC = s[begin]
            if (lookUpMap.contains(tempC)) {
                lookUpMap[tempC] = lookUpMap[tempC]!! + 1
                if (lookUpMap[tempC]!! > 0) {
                    counter++
                }
            }
            if (end - begin < subStringLength) {
                output = s.substring(begin, end)
                subStringLength = output.length
            }
            begin++
        }
    }
    return output
}

