package org.example.korutyny.sync

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object MainThread {

    fun main() = runBlocking {

        println("POCZATEK")

        val stingJob = launch {
            val st = Sting(2)
            st.start()
        }
//        stingJob.join()

        val krawczykJob = launch {
            val krawczyk = Krawczyk()
            krawczyk.start()
        }
//        krawczykJob.join()

        SlowPrinter.instance.print("Jestem glownym watkiem aplikacji.")

        println("KONIEC")
    }

    private class Krawczyk {
        suspend fun start() {
            SlowPrinter.instance.print("Parostatkiem piekny rejs ... ")
//            return "Parostatkiem piekny rejs ... "
        }
    }
}