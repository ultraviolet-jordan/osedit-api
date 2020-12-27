package com.osrsd.cache.provider

import com.osrsd.spring.domain.Definition
import com.osrsd.cache.util.ByteBufferExt
import com.osrsd.spring.domain.EnumDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object EnumProvider {

    private val log: Logger = LoggerFactory.getLogger(EnumProvider::class.java)

    fun decode(buffer: ByteBuffer, definition: EnumDefinition): Definition {
        do {
            when (val opcode: Int = buffer.get().toInt() and 0xff) {
                1 -> definition.keyType = (buffer.get().toInt() and 0xff).toChar()
                2 -> definition.valType = (buffer.get().toInt() and 0xff).toChar()
                3 -> definition.defaultString = ByteBufferExt.getString(buffer)
                4 -> definition.defaultInt = buffer.int
                5 -> {
                    val length: Int = buffer.short.toInt() and 0xffff
                    (0 until length).forEach { _ ->
                        val key = buffer.int
                        val value = ByteBufferExt.getString(buffer)
                        definition.params[key.toLong()] = value
                    }
                }
                6 -> {
                    val length: Int = buffer.short.toInt() and 0xffff
                    (0 until length).forEach { _ ->
                        val key = buffer.int
                        val value: Int = buffer.int
                        definition.params[key.toLong()] = value.toString()
                    }
                }
                0 -> break
                else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
            }
        } while (true)
        return definition
    }

}
