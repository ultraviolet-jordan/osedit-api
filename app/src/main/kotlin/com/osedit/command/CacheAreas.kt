package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.AreaLoader
import com.osedit.spring.App
import com.osedit.spring.domain.AreaDefinition
import java.util.concurrent.CountDownLatch

class CacheAreas(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(AreaDefinition::class.java, AreaLoader().load().definitions)
        App.prompt(CacheAreas::class.java, start)
        latch.countDown()
    }

}