package org.example.obiektowe

class Bbb(py: Int) : Aaa(py) {
    var z: Int = 11

    init {
        x = 0
    }

    init {
        y = 0
    }

    override fun toString(): String {
        val base = super.toString()
        return "[$base; $z]"
    }

    companion object {
        fun main() {
            val a = Aaa(5)

            wypisz(a)

            val b: Aaa = Bbb(9)
//            b.x = 3  // błąd kompilatora nie mozna sie dostac do protected
            b.y = 8

            wypisz(b)
        }

        fun wypisz(z: Aaa) {
            println("{$z}")
        }
    }
}
