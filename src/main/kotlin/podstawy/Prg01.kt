package org.example.basics

object Prg01 {

    fun main() {

        //Deklaracja zmiennych
        var x: Int = 2
        var y: Int = 5


        println("x=$x, y=$y")























        //Zamiana wartosci zmiennych przy uzyciu trzeciej zmiennej
        val tmp: Int = x
        x = y
        y = tmp



























        //Zamiana wartosci zmiennych bez uzycia trzeciej zmiennej
        x = x + y
        y = x - y
        x = x - y



























        //Wartoscia instrukcji przypisania jest wartosc po prawej stronie (rvalue)
        y = 6.also{ x = it }
//        println(x)
//        println(y)

























        //Petla nieskonczona
        while (true) {
//            println("hop")
            break
        }
























        //Deklaracja tablicy
        val a: IntArray = intArrayOf(1, 3, 5)


























        //Iteracja po elementach tablicy
        for (z: Int in a) {
//            println(z)
        }























        //Iteracja po indeksach tablicy
        for (i in a.indices) {
            a[i] = 1
        }
    }
}