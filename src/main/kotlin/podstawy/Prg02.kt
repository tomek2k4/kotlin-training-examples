package org.example.basics

object Prg02 {

    fun main() {

        tablice()

    }

    fun tablice() {


        //Demonstracja operator modulo
        println(12 % 5)
        println((-12) % 5)
        println(12 % (-5))







        //Deklaracja tablicy
        val tab = IntArray(10)










        //Iteracja po indeksach tablicy
        for (i in tab.indices) {
            tab[i] = i
        }







        //Iteracja po elementach tablicy
        for (a in tab) {
            println(a)
        }






        //Obliczenie sredniej elementow tablicy
        var sum = 0
        for (a in tab) {
            sum += a
        }
        println(sum / tab.size.toDouble())
    }











    fun tablica2() {
        var s = "  4    6   9 "











        s = s.trim().replace("\\s+".toRegex(), ",")

        val numbers: Array<Int> = s.split(",").map { it.toInt() }.toTypedArray()

        numbers.forEach {
            println(it)
        }
    }
}