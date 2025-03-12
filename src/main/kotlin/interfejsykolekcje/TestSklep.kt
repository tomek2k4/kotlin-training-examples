package org.example.interfejsykolekcje

object TestSklep {

    fun main() {
        val k = Koszyk()

        k.add(Piwo("Zywiec", 3.2))
        k.add(Ziemniaki(2.0))
        k.add(Piwo("Zywiec", 3.2))
        k.add(Rabat(Piwo("Ciechan", 4.0), 20.0))

        println(k.lista())
        println(k.rachunek())
    }
}