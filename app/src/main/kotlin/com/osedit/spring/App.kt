package com.osedit.spring

import com.osedit.command.CacheProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

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

        //Cache definitions
        CacheProvider.execute()

        log.info(String.format("Starting Spring after %sms.", System.currentTimeMillis() - start))
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
        fun prompt(className: String, start: Long) {
            log.info(String.format("%s took %sms to cache.", className, System.currentTimeMillis() - start))
        }
    }

}