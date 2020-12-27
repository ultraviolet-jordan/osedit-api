package com.osrsd.spring

import com.osrsd.command.PrintEnums
import com.osrsd.command.PrintHealthBars
import com.osrsd.command.PrintItems
import com.osrsd.command.PrintNpcs
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
    fun boot(resource: String) {
        App::class.java.getResourceAsStream(resource).use {
            properties.load(it)
        }

        val latch = CountDownLatch(4)
        val commands = listOf(
                //PrintInvs(latch),
                //PrintObjects(latch),
                PrintEnums(latch),
                PrintNpcs(latch),
                PrintItems(latch),
                //PrintParams(latch),
                //PrintSequences(latch),
                //PrintSpotAnimations(latch),
                //PrintVarbits(latch),
                //new PrintSoundEffects(latch),
                //new PrintSprites(latch),
                //new PrintTextures(latch),
                PrintHealthBars(latch),
                //PrintAreas(latch),
                //PrintScripts(latch)
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
        runApplication<App>()
    }

    companion object {
        /**
         * The main logger.
         */
        private val log: Logger = LoggerFactory.getLogger(App::class.java)

        /**
         * The application properties.
         */
        private val properties = Properties()

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
            log.info(String.format("%s took %sms to dump.", command.simpleName, System.currentTimeMillis() - start))
        }
    }

}