package org.example.korutyny.sync

import java.util.*

object CzasReakcji {
    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val scn = Scanner(System.`in`)

        val r = Random(Date().time)

        val tms = r.nextInt(2000) + 2000

        Thread.sleep(tms.toLong())

        print("HOP")
        System.out.flush()
        val d1 = Date()

        scn.nextLine()
        val d2 = Date()

        println("Czas reakcji: " + (d2.time - d1.time))
    }
}
