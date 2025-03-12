package org.example.interfejsykolekcje

class Ziemniaki(waga: Double) : NaWage(waga, CENA) {
    override val nazwa: String
        get() = "Ziemniak ($waga kg)"

    companion object {
        const val CENA: Double = 1.2
    }
}
