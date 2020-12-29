package com.osedit.spring

import com.osedit.command.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootApplication
open class App {

    /**
     * Starts the main application.
     */
    fun boot() {
        val start: Long = System.currentTimeMillis()
        App::class.java.getResourceAsStream(resource).use {
            properties.load(it)
        }

        //Latch is necessary.
        val latch = CountDownLatch(13)
        val commands = listOf(
                CacheInvs(latch),
                CacheObjects(latch),
                CacheEnums(latch),
                CacheNpcs(latch),
                CacheItems(latch),
                CacheParams(latch),
                CacheSequences(latch),
                CacheSpotAnimations(latch),
                CacheVarbits(latch),
                CacheHealthBars(latch),
                CacheAreas(latch),
                CacheOverlays(latch),
                CacheMaps(latch)
        )
        val cores = Runtime.getRuntime().availableProcessors()
        if (cores > 4) {
            val pool = Executors.newFixedThreadPool(cores)
            commands.forEach(pool::execute)
            pool.shutdown()
        } else {
            commands.forEach(Runnable::run)
        }
        latch.await()
        log.info(String.format("Starting Spring after %sms.", System.currentTimeMillis() - start))
        //The spring application.
        runApplication<App>()
    }

    companion object {
        /**
         * The app properties resource path.
         */
        const val resource: String = "/application.properties"

        /**
         * The application properties.
         */
        val properties = Properties()

        /**
         * The main logger.
         */
        private val log: Logger = LoggerFactory.getLogger(App::class.java)

        /**
         * The base directory for serializing data.
         */
        fun baseDirectory(): String {
            return String.format("./%s", properties.getProperty("cache.version"))
        }

        /**
         * Prompts the application console with performance numbers.
         */
        fun prompt(command: Class<out Runnable>, start: Long) {
            log.info(String.format("%s took %sms to cache.", command.simpleName, System.currentTimeMillis() - start))
        }
    }

}