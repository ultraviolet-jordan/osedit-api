package com.osedit.cache.provider

import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.SpotAnimationDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object SpotAnimationProvider : Provider<SpotAnimationDefinition> {

    private val log: Logger = LoggerFactory.getLogger(SpotAnimationProvider::class.java)

    override fun decode(buffer: ByteBuffer, definition: SpotAnimationDefinition): Definition {
        do {
            when (val opcode: Int = buffer.get().toInt() and 0xff) {
                1 -> definition.modelId = buffer.short.toInt() and 0xffff
                2 -> definition.animationId = buffer.short.toInt() and 0xffff
                4 -> definition.resizeX = buffer.short.toInt() and 0xffff
                5 -> definition.resizeY = buffer.short.toInt() and 0xffff
                6 -> definition.rotation = buffer.short.toInt() and 0xffff
                7 -> definition.ambient = buffer.get().toInt() and 0xff
                8 -> definition.contrast = buffer.get().toInt() and 0xff
                40 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.recolorToFind = ShortArray(length)
                    definition.recolorToReplace = ShortArray(length)
                    (0 until length).forEach {
                        definition.recolorToFind!![it] = (buffer.short.toInt() and 0xffff).toShort()
                        definition.recolorToReplace!![it] = (buffer.short.toInt() and 0xffff).toShort()
                    }
                }
                41 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.textureToFind = ShortArray(length)
                    definition.textureToReplace = ShortArray(length)
                    (0 until length).forEach {
                        definition.textureToFind!![it] = (buffer.short.toInt() and 0xffff).toShort()
                        definition.textureToReplace!![it] = (buffer.short.toInt() and 0xffff).toShort()
                    }
                }
                0 -> break
                else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
            }
        } while (true)
        return definition
    }

}
