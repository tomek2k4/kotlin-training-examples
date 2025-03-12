package org.example.obiektowe

open class Aaa(
    var y: Int = 0
) {
    protected var x: Int = 4


    override fun toString(): String {
        return "($x, $y)"
    }
}