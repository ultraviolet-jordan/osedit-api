package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.EnumLoader
import com.osedit.spring.App
import com.osedit.spring.domain.EnumDefinition
import java.util.concurrent.CountDownLatch

class CacheEnums(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(EnumDefinition::class.java, EnumLoader().load().definitions)
        App.prompt(CacheEnums::class.java, start)
        latch.countDown()
    }

}