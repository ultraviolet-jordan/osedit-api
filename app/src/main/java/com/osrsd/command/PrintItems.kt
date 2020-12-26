package com.osrsd.command

import com.osrsd.cache.Library
import com.osrsd.cache.loader.ItemLoader
import com.osrsd.spring.App
import com.osrsd.spring.domain.ItemDefinition
import java.util.concurrent.CountDownLatch

class PrintItems(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start = System.currentTimeMillis()
        Library.store(ItemDefinition::class.java, ItemLoader().load().definitions)
        App.prompt(PrintItems::class.java, start)
        latch.countDown()
    }

}