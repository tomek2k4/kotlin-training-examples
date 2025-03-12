package org.example.obiektowe

import java.math.BigInteger

object Rekursja {

    fun main() {
        println(hanoi(4))
        println(hanoi2(BigInteger("980")))
        println(fibonacci(13))
    }

    fun hanoi(n: Long): Long {
        return if (n < 2) {
            1
        } else {
            2 * hanoi(n - 1) + 1
        }
    }

    fun hanoi2(n: BigInteger): BigInteger {
        return if (n == BigInteger.ONE) {
            BigInteger.ONE
        } else {
            (BigInteger("2")).multiply(hanoi2(n.subtract(BigInteger.ONE))).add(BigInteger.ONE)
        }
    }

    fun fibonacci(n: Int): Int {
        return when {
            n == 0 -> 0
            n == 1 -> 1
            else -> fibonacci(n - 1) + fibonacci(n - 2)
        }
    }
}