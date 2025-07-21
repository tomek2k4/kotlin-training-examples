package org.example.algorytmy.roman

fun main() {
    /*
    Example 1:
    Input: s = "III"
    Output: 3
    Explanation: III = 3.

    Example 2:
    Input: s = "LVIII"
    Output: 58
    Explanation: L = 50, V= 5, III = 3.

    Example 3:
    Input: s = "MCMXCIV"
    Output: 1994
    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
    */
    println(romanToInteger("MCMXCIV"))
}

fun romanToInteger(str: String): Int {
    var output: Int = 0
    var end = str.length - 1
    var right = RomanEnum.I // dummy init of lowest number
    while (end >= 0) {
        RomanEnum.valueOf(str[end].toString()).let {
            if (it >= right) {
                output += it.decimal
            } else {
                output -= it.decimal
            }
            right = it
        }
        end--
    }
    return output
}

enum class RomanEnum(val decimal: Int) {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000)
}