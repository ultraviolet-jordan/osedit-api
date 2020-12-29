package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.AreaLoader
import com.osedit.cache.loader.MapLoader
import com.osedit.cache.loader.OverlayLoader
import com.osedit.spring.App
import com.osedit.spring.domain.AreaDefinition
import com.osedit.spring.domain.MapDefinition
import com.osedit.spring.domain.OverlayDefinition
import com.osedit.spring.service.MapArchiveKeyService
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CountDownLatch

class CacheMaps(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(MapDefinition::class.java, MapLoader().load().definitions)
        App.prompt(CacheMaps::class.java, start)
        latch.countDown()
    }

}