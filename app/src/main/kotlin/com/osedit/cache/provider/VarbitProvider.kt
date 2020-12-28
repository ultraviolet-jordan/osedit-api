package com.osedit.cache.provider

import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.VarbitDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object VarbitProvider {

    private val log: Logger = LoggerFactory.getLogger(VarbitProvider::class.java)

    fun decode(buffer: ByteBuffer, definition: VarbitDefinition): Definition {
        do when (val opcode: Int = buffer.get().toInt() and 0xff) {
            1 -> {
                definition.index = buffer.short.toInt() and 0xffff
                definition.leastSignificantBit = buffer.get().toInt() and 0xff
                definition.mostSignificantBit = buffer.get().toInt() and 0xff
            }
            0 -> break
            else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
        } while (true)
        return definition
    }

}
