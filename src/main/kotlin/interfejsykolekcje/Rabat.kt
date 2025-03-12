package org.example.interfejsykolekcje


class Rabat(private val towar: Towar, private val procent: Double) : Towar {
    override val cena: Double
        get() = towar.cena * (1.0 - procent / 100.0)

    override val nazwa: String
        get() = towar.nazwa + "*"
}
