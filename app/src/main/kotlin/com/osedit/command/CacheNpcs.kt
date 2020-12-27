package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.NpcLoader
import com.osedit.spring.App
import com.osedit.spring.domain.NpcDefinition
import java.util.concurrent.CountDownLatch

class CacheNpcs(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(NpcDefinition::class.java, NpcLoader().load().definitions)
        App.prompt(CacheNpcs::class.java, start)
        latch.countDown()
    }

}