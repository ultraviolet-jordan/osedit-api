package com.osrsd.command

import com.osrsd.cache.Library
import com.osrsd.cache.loader.EnumLoader
import com.osrsd.cache.loader.HealthBarLoader
import com.osrsd.cache.loader.ItemLoader
import com.osrsd.cache.loader.NpcLoader
import com.osrsd.spring.App
import com.osrsd.spring.domain.EnumDefinition
import com.osrsd.spring.domain.HealthBarDefinition
import com.osrsd.spring.domain.ItemDefinition
import com.osrsd.spring.domain.NpcDefinition
import java.util.concurrent.CountDownLatch

class PrintHealthBars(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start = System.currentTimeMillis()
        Library.store(HealthBarDefinition::class.java, HealthBarLoader().load().definitions)
        App.prompt(PrintHealthBars::class.java, start)
        latch.countDown()
    }

}