package org.example.obiektowe

object Napisy2 {

    fun main() {
        val tab = intArrayOf(4, 3, 6, 1, 8, 9)

        //ZLO!!!!
        var buf = ""
        for (x in tab) {
            buf = "$buf$x; "
        }
        println(buf)

        if (buf.startsWith("4;")) {
            println("Hurra")
        }

        val buffer = StringBuffer()
        for (x in tab) {
            buffer.append(x).append("; ")
        }
        println(buffer)

        val kotlinWay = tab.joinToString("; ", postfix = ";")
        println(kotlinWay)
    }
}
