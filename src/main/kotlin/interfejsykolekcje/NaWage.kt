package org.example.interfejsykolekcje

abstract class NaWage(protected var waga: Double, private val cenaZaKg: Double) : Towar {
    override val cena: Double
        get() = waga * cenaZaKg
}
