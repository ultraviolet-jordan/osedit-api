package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.ObjectLoader
import com.osedit.spring.App
import com.osedit.spring.domain.ObjectDefinition
import java.util.concurrent.CountDownLatch

class CacheObjects(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(ObjectDefinition::class.java, ObjectLoader().load().definitions)
        App.prompt(CacheObjects::class.java, start)
        latch.countDown()
    }

}