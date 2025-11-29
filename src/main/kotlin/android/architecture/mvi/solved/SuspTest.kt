package org.example.android.architecture.mvi.solved

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SuspTest {

    private val myData: Int = 1

    suspend fun main(): Int {
        runBlocking {
            delay(10)
            return@runBlocking 2
        }
        return 0
    }

}