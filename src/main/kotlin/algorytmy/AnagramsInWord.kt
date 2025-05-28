package org.example.algorytmy


fun main() {
    /*
    Find all starting indexes in string "k" that contains anagrams of string "o"

    Example 1:
    Input: o = "ab", k = "abacab"
    Output: [0,1,4]

    Example 2:
    Input: o = "abc", k = "abacab"
    Output: [1,3]
    */
    println("Hello World!")

    println(findAllContains("abc", "abacab").toList())
}

fun findAllContains(o: String?, k: String?): Array<Int> {
    if (o.isNullOrEmpty()) return emptyArray()
    if (k.isNullOrEmpty()) return emptyArray()
    if (k.length < o.length) return emptyArray()

    val outputIndexes = mutableListOf<Int>()
    var testedWord: String
    val anagramsList = permutation(o)

    for (i in 0..k.length - o.length) {
        testedWord = k.substring(i, i + o.length)
        anagramsList.forEach {
            if (testedWord == it) outputIndexes.add(i)
        }
    }
    return outputIndexes.toTypedArray()
}

fun permutation(word: String): List<String> {
    val permutationList = mutableListOf<String>()
    permutation("", word, permutationList)
    return permutationList.toList()
}

fun permutation(prefix: String, word: String, permutationList: MutableList<String>) {
    val n = word.length
    if (n == 0 || n == 1) {
        permutationList.add(prefix + word)
    } else {
        for (i in 0..<n) {
            permutation(prefix + word[i], word.substring(0, i) + word.substring(i + 1, n), permutationList)
        }
    }
}
