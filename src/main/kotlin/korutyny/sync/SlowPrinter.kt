package org.example.korutyny.sync

import kotlinx.coroutines.delay
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock

class SlowPrinter private constructor() // public synchronized void print(String txt) {
{
    private val printerLock: Lock = ReentrantLock()

    suspend fun print(txt: String) {
        printerLock.lock()
        try {
            for (element in txt) {
                printCharacter(element)
                try {
//                        Thread.sleep(PRINTER_SLEEP_INTERVAL_MS.toLong())
                    delay(PRINTER_SLEEP_INTERVAL_MS)
                } catch (ex: InterruptedException) {
                    ex.printStackTrace()
                }
            }
        } finally {
            printerLock.unlock()
        }
    }

    private fun printCharacter(c: Char) {
        print(c)
        System.out.flush()
    }

    companion object {
        private const val PRINTER_SLEEP_INTERVAL_MS = 100L
        val instance: SlowPrinter = SlowPrinter()
    }
}
