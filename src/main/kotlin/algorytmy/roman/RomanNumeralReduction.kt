package org.example.algorytmy.roman

fun main() {
    /*
    Example 1:
    Input: XXXVVIIIIIIIIII
    Output: "L"

    Example 2:
    Input: DDLL
    Output: "MC"
    */

    println(romanNumeralReduction("XXXVVIIIIIIIIII"))
}

fun romanNumeralReduction(str: String): String {

    return integerToRoman(romanToInteger(str))
}

//private enum class RomanEnum(val decimal: Int) {
//    I(1),
//    V(5),
//    X(10),
//    L(50),
//    C(100),
//    D(500),
//    M(1000)
//}