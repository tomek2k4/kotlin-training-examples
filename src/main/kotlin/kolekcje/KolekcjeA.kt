package org.example.kolekcje

import java.util.*
import kotlin.collections.ArrayList

object KolekcjeA {
    fun main() {
        var imiona: MutableCollection<String>

        imiona = ArrayList<String>();
//        imiona = Stack<String>();
//        imiona = TreeSet<String>(); //Uwaga to nie implementuje
//        List<String>
        imiona.add("Przemek")
        imiona.add("Beata")
        imiona.add("Andrzej")
        imiona.add("Przemek")
        imiona.add("Tomasz")

        imiona.add(3, "Katarzyna")

        for (n in imiona.withIndex()) {
            println(n)
        }
    }
}
