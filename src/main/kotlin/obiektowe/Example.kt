package org.example.obiektowe

class Example {

    var x: Int = 0

    fun printX() {
        println(x)
        Y++
    }

    companion object {
        var Y: Int = 0

        fun printY() {
            println(Y)
        }
    }
}
