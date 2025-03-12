package org.example.wyjatki

import java.io.FileNotFoundException

object Wyjatki {

    @Throws(FileNotFoundException::class)
    fun doSomethingInside() {
        val a = 233
        val b = 0

        try {
            val c = a / b
            println(c)
        } catch (t: ArithmeticException) {
            println("Cos zlego sie stalo!")
            t.printStackTrace()
        }

        throw FileNotFoundException()
    }

    @Throws(FileNotFoundException::class)
    fun doSomething() {
        doSomethingInside()
    }

    fun main() {
        try {
            doSomething()
        } catch (ex: FileNotFoundException) {
            ex.printStackTrace()
        }
    }

}