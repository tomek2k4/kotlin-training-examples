package org.example.interfejsykolekcje

class Koszyk {
    private val towary: MutableCollection<Towar> = ArrayList()

    fun add(t: Towar) {
        towary.add(t)
    }

    fun rachunek(): Double {
        var r = 0.0
        for (t in towary) {
            r = r + t.cena
        }

        return r
    }

    fun lista(): String {
        val lista = StringBuilder()
        var i = 1
        for (t in towary) {
            lista.append(i++).append(". ").append(t.nazwa).append(": ").append(t.cena).append("zl")
                .append("\n")
        }
        return lista.toString()
    }
}
