package com.osedit.cache.provider

import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.OverlayDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object OverlayProvider {

    private val log: Logger = LoggerFactory.getLogger(OverlayProvider::class.java)

    fun decode(buffer: ByteBuffer, definition: OverlayDefinition): Definition {
        do {
            when (val opcode: Int = buffer.get().toInt() and 0xff) {
                1 -> definition.rgbColor = (((buffer.get().toInt() and 0xff) shl 16) + ((buffer.get().toInt() and 0xff) shl 8) + (buffer.get().toInt() and 0xff))
                2 -> definition.textureId = buffer.get().toInt() and 0xff
                5 -> definition.hideUnderlay = false
                7 -> definition.secondaryRgbColor = (((buffer.get().toInt() and 0xff) shl 16) + ((buffer.get().toInt() and 0xff) shl 8) + (buffer.get().toInt() and 0xff))
                0 -> break
                else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
            }
        } while (true)

        definition.calculateHsl()
        return definition
    }
}