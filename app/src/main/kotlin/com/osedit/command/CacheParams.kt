package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.ParamLoader
import com.osedit.spring.App
import com.osedit.spring.domain.ParamDefinition
import java.util.concurrent.CountDownLatch

class CacheParams(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(ParamDefinition::class.java, ParamLoader().load().definitions)
        App.prompt(CacheParams::class.java, start)
        latch.countDown()
    }

}