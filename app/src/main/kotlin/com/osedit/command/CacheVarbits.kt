package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.VarbitLoader
import com.osedit.spring.App
import com.osedit.spring.domain.VarbitDefinition
import java.util.concurrent.CountDownLatch

class CacheVarbits(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(VarbitDefinition::class.java, VarbitLoader().load().definitions)
        App.prompt(CacheVarbits::class.java, start)
        latch.countDown()
    }

}