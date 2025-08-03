package org.example.korutyny.semaphore

import kotlinx.coroutines.*

fun main() {
    // method 1
    val fixedThreadPoolContext = newFixedThreadPoolContext(2, "Pool")

    // method 2
    val limitedParallelismContext = Dispatchers.IO.limitedParallelism(2)

    runBlocking {
        val jobs = (1..100).map {
            // swap out the dispatcher here
            launch(limitedParallelismContext) {
                println("started $it")
                Thread.sleep(200)
                println("    finished $it")
            }
        }
        jobs.joinAll()
    }
}