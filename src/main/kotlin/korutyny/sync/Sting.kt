package org.example.korutyny.sync

import kotlinx.coroutines.delay

class Sting(val repeat: Int = 10) {

    suspend fun start() {
        for (i in 0..<repeat) {
            SlowPrinter.instance.print("I'm an english man in New York ...")
            try {
                delay(500)
            } catch (ex: InterruptedException) {
                // TODO Auto-generated catch block
                ex.printStackTrace()
            }
        }
    }
}