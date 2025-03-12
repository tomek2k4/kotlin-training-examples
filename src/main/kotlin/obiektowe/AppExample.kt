package org.example.obiektowe

object AppExample {

    fun main() {
        val e = Example()
        val f = Example()

        e.x = 10
        Example.Y = 6

        f.x = 101
        Example.Y = 81

        e.printX()
        Example.printY()

        f.printX()
        Example.printY()

        Example.Y = 99
        Example.printY()
    }
}
