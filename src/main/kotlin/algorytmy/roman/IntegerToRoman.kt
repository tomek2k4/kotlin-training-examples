package org.example.algorytmy.roman

fun main() {
    /*
    Example 1:
    Input: num = 3749
    Output: "MMMDCCXLIX"
    Explanation:
    3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
     700 = DCC as 500 (D) + 100 (C) + 100 (C)
      40 = XL as 10 (X) less of 50 (L)
       9 = IX as 1 (I) less of 10 (X)
    Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places

    Example 2:
    Input: num = 58
    Output: "LVIII"
    Explanation:
    50 = L
     8 = VIII

    Example 3:
    Input: num = 1994
    Output: "MCMXCIV"
    Explanation:
    1000 = M
     900 = CM
      90 = XC
       4 = IV
    */
    println(integerToRoman(1994))
}

fun integerToRoman(num: Int): String {
    val output = StringBuilder()
    val romanList = Roman.entries
    var digit: Int
    var magnitude = 0
    var carry: Int = num
    while (carry != 0) {
        digit = carry % 10
        when {
            digit <= 3 -> {
                output.append(romanList[magnitude].name.repeat(digit))
            }

            digit <= 5 -> {
                output.append(romanList[magnitude + 1])
                output.append(romanList[magnitude].name.repeat(5 - digit))
            }

            digit <= 8 -> {
                output.append(romanList[magnitude].name.repeat(digit - 5))
                output.append(romanList[magnitude + 1])
            }

            else -> {
                output.append(romanList[magnitude + 2])
                output.append(romanList[magnitude].name.repeat(10 - digit))
            }
        }
        carry /= 10
        magnitude += 2
    }
    return output.reverse().toString()
}


enum class Roman {
    I,//1
    V,//5
    X,//10
    L,//50
    C,//100
    D,//500
    M;//1000
}
