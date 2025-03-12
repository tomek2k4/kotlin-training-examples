package org.example.korutyny.watki

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object MainThread {

    fun main() = runBlocking {

        println("POCZATEK")

        launch {
            val st = Sting(2)
            st.start()
        }

        launch {
            val krawczyk = Krawczyk()
            krawczyk.start()
        }

        SlowPrinter.instance.print("Jestem glownym watkiem aplikacji.")

        println("KONIEC")
    }

    private class Krawczyk {
        suspend fun start() {
            SlowPrinter.instance.print("Parostatkiem piekny rejs ... ")
        }
    }
}