package com.osrsd.command

import com.osrsd.cache.Library
import com.osrsd.cache.loader.ItemLoader
import com.osrsd.cache.loader.NpcLoader
import com.osrsd.spring.App
import com.osrsd.spring.domain.ItemDefinition
import com.osrsd.spring.domain.NpcDefinition
import java.util.concurrent.CountDownLatch

class PrintNpcs(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start = System.currentTimeMillis()
        Library.store(NpcDefinition::class.java, NpcLoader().load().definitions)
        App.prompt(PrintNpcs::class.java, start)
        latch.countDown()
    }

}