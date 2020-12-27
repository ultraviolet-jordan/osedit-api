package com.osedit.command

import com.osedit.cache.Library
import com.osedit.cache.loader.InvLoader
import com.osedit.spring.App
import com.osedit.spring.domain.InvDefinition
import java.util.concurrent.CountDownLatch

class CacheInvs(val latch: CountDownLatch) : Runnable {

    override fun run() {
        val start: Long = System.currentTimeMillis()
        Library.store(InvDefinition::class.java, InvLoader().load().definitions)
        App.prompt(CacheInvs::class.java, start)
        latch.countDown()
    }

}