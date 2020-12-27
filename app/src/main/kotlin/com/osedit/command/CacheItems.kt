package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.ItemLoader
import com.osedit.spring.App
import com.osedit.spring.domain.ItemDefinition
import java.util.concurrent.CountDownLatch

class CacheItems(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(ItemDefinition::class.java, ItemLoader().load().definitions)
        App.prompt(CacheItems::class.java, start)
        latch.countDown()
    }

}