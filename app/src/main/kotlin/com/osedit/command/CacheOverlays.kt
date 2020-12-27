package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.AreaLoader
import com.osedit.cache.loader.OverlayLoader
import com.osedit.spring.App
import com.osedit.spring.domain.AreaDefinition
import com.osedit.spring.domain.OverlayDefinition
import java.util.concurrent.CountDownLatch

class CacheOverlays(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(OverlayDefinition::class.java, OverlayLoader().load().definitions)
        App.prompt(CacheOverlays::class.java, start)
        latch.countDown()
    }

}