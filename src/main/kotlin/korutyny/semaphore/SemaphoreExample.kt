package org.example.korutyny.semaphore

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

fun main(): Unit = runBlocking {
    val semaphore = Semaphore(2)

    try {
        coroutineScope {
            (1..100).map {
                // swap out the dispatcher here
                launch(Dispatchers.IO) {
                    semaphore.withPermit {
                        println("started $it")
                        delay(200)
                        if (it == 40) throw IllegalArgumentException()
                        println("    finished $it")
                    }
                }
            }
        }
    } catch (exception: Exception) {
        val message = "Exception occurred! Number of available is: ${semaphore.availablePermits}"
        println(message)
//            throw Error(message)
    }
}