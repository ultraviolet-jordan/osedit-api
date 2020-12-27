package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.SequenceLoader
import com.osedit.spring.App
import com.osedit.spring.domain.SequenceDefinition
import java.util.concurrent.CountDownLatch

class CacheSequences(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(SequenceDefinition::class.java, SequenceLoader().load().definitions)
        App.prompt(CacheSequences::class.java, start)
        latch.countDown()
    }

}