package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.HealthBarLoader
import com.osedit.spring.App
import com.osedit.spring.domain.HealthBarDefinition
import java.util.concurrent.CountDownLatch

class CacheHealthBars(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(HealthBarDefinition::class.java, HealthBarLoader().load().definitions)
        App.prompt(CacheHealthBars::class.java, start)
        latch.countDown()
    }

}