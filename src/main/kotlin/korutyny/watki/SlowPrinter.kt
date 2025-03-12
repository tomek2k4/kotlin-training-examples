package org.example.korutyny.watki

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SlowPrinter private constructor() {

    suspend fun print(txt: String) {
        for (element in txt) {
            printCharacter(element)
            try {
                delay(PRINTER_SLEEP_INTERVAL_MS.toLong())
//                Thread.sleep(PRINTER_SLEEP_INTERVAL_MS.toLong())
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }
        }
    }

    private fun printCharacter(c: Char) {
        print(c)
        System.out.flush()
    }

    companion object {
        private const val PRINTER_SLEEP_INTERVAL_MS = 250
        val instance: SlowPrinter = SlowPrinter()
    }
}