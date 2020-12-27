package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.SpotAnimationLoader
import com.osedit.spring.App
import com.osedit.spring.domain.SpotAnimationDefinition
import java.util.concurrent.CountDownLatch

class CacheSpotAnimations(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(SpotAnimationDefinition::class.java, SpotAnimationLoader().load().definitions)
        App.prompt(CacheSpotAnimations::class.java, start)
        latch.countDown()
    }

}