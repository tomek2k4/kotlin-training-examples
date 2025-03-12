package org.example.interfejsykolekcje

data class Piwo(private val marka: String, override val cena: Double) : Towar {
    override val nazwa: String
        get() = "Piwo $marka"
}
