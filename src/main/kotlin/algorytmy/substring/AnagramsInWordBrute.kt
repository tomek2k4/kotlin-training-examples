package org.example.algorytmy.substring


fun main() {
    /*
    Find all starting indexes in string "k" that contains anagrams of string "o"

    Example 1:
    Input: s = "abacab", t = "ab"
    Output: [0,1,4]

    Example 2:
    Input: s = "abacab", t = "abc"
    Output: [1,3]
    */
    println("Hello World!")

    println(findAllContains("abacab", "ab").toList())
}

fun findAllContains(s: String?, t: String?): Array<Int> {
    val outputIndexes = java.util.LinkedList<Int>()
    if (s.isNullOrEmpty() || t.isNullOrEmpty() || s.length < t.length) return outputIndexes.toTypedArray()

    var testedWord: String
    val anagramsList = permutation(t)

    for (i in 0..s.length - t.length) {
        testedWord = s.substring(i, i + t.length)
        anagramsList.forEach {
            if (testedWord == it) outputIndexes.add(i)
        }
    }
    return outputIndexes.toTypedArray()
}

fun permutation(word: String): List<String> {
    val permutationSet = mutableSetOf<String>()
    permutation("", word, permutationSet)
    return permutationSet.toList()
}

fun permutation(prefix: String, word: String, permutationSet: MutableSet<String>) {
    val n = word.length
    if (n == 0 || n == 1) {
        permutationSet.add(prefix + word)
    } else {
        for (i in 0..<n) {
            permutation(prefix + word[i], word.substring(0, i) + word.substring(i + 1, n), permutationSet)
        }
    }
}
