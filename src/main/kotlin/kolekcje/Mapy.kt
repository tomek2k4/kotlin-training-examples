package org.example.kolekcje

import java.util.*
import kotlin.collections.HashMap

object Mapy {

    fun main() {
        val oceny: MutableMap<String, Int> = TreeMap() // Tree map pozwala na sortowanie na bieżąco elementow po kluczu

        oceny["Przemek"] = 5 // autoboxing 5 -> Integer(5)
        oceny["Katarzyna"] = 3
        oceny["Przemek"] = 4
        oceny["Piotr"] = 1

        val ocenaPrzemka = oceny["Przemek"]
        println(ocenaPrzemka)
        println("----------------------")

        for (k in oceny.keys) {
            println(k + " -> " + oceny[k])
        }
        println("----------------------")

        for ((key, value) in oceny) {
            println("$key -> $value")
        }
        println("----------------------")

        oceny.forEach { (key, value) ->
            println("$key -> $value")
        }
    }

}