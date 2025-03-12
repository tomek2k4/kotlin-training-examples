package org.example.korutyny.watki

class Sting(private val repeat: Int = 10) {

    suspend fun start() {
        for (i in 0..<repeat) {
            SlowPrinter.instance.print("I'm an english man in New York ...")
        }
    }
}