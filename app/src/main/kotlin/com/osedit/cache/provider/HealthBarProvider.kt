package com.osedit.cache.provider

import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.HealthBarDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object HealthBarProvider {

    private val log: Logger = LoggerFactory.getLogger(HealthBarProvider::class.java)

    fun decode(buffer: ByteBuffer, definition: HealthBarDefinition): Definition {
        do when (val opcode: Int = buffer.get().toInt() and 0xff) {
            1 -> definition.field3276 = buffer.short.toInt() and 0xffff
            2 -> definition.field3277 = buffer.get().toInt() and 0xff
            3 -> definition.field3278 = buffer.get().toInt() and 0xff
            4 -> definition.field3283 = 0
            5 -> definition.field3275 = buffer.short.toInt() and 0xffff
            6 -> definition.field3272 = buffer.get().toInt() and 0xff
            7 -> definition.healthBarFrontSpriteId = buffer.short.toInt() and 0xffff
            8 -> definition.healthBarBackSpriteId = buffer.short.toInt() and 0xffff
            11 -> definition.field3283 = buffer.short.toInt() and 0xffff
            14 -> definition.healthScale = buffer.get().toInt() and 0xff
            15 -> definition.healthBarPadding = buffer.get().toInt() and 0xff
            0 -> break
            else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
        } while (true)
        return definition
    }

}
