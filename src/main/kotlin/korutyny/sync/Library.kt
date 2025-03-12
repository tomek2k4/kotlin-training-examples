package org.example.korutyny.sync

import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock

class Library {
    private val rwLock: ReadWriteLock = ReentrantReadWriteLock()

    fun read(title: String?): String {
        val l = rwLock.readLock()
        l.lock()
        try {
            return "content"
        } finally {
            l.unlock()
        }
    }

    fun write(title: String?, content: String?) {
        val l = rwLock.writeLock()
        l.lock()
        try {
            //TODO
        } finally {
            l.unlock()
        }
    }
}
