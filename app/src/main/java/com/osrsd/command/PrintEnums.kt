package com.osrsd.command

import com.osrsd.cache.Library
import com.osrsd.cache.loader.EnumLoader
import com.osrsd.cache.loader.ItemLoader
import com.osrsd.cache.loader.NpcLoader
import com.osrsd.spring.App
import com.osrsd.spring.domain.EnumDefinition
import com.osrsd.spring.domain.ItemDefinition
import com.osrsd.spring.domain.NpcDefinition
import java.util.concurrent.CountDownLatch

class PrintEnums(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start = System.currentTimeMillis()
        Library.store(EnumDefinition::class.java, EnumLoader().load().definitions)
        App.prompt(PrintEnums::class.java, start)
        latch.countDown()
    }

}