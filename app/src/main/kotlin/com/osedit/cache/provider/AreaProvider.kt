package com.osedit.cache.provider

import com.osedit.cache.util.ByteBufferExt
import com.osedit.spring.domain.AreaDefinition
import com.osedit.spring.domain.Definition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object AreaProvider : Provider<AreaDefinition> {

    private val log: Logger = LoggerFactory.getLogger(AreaProvider::class.java)

    override fun decode(buffer: ByteBuffer, definition: AreaDefinition): Definition {
        do {
            when (val opcode: Int = buffer.get().toInt() and 0xff) {
                1 -> definition.spriteId = buffer.short.toInt() and 0xffff
                2 -> definition.field3294 = buffer.short.toInt() and 0xffff
                3 -> definition.name = ByteBufferExt.getString(buffer)
                4 -> definition.tileHash = ByteBufferExt.getMedium(buffer)
                5 -> ByteBufferExt.getMedium(buffer)
                6 -> definition.field3310 = buffer.get().toInt() and 0xff
                7 -> buffer.get()
                8 -> buffer.get()
                in 10..14 -> definition.field3298[opcode - 10] = ByteBufferExt.getString(buffer)
                15 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.field3300 = IntArray(length * 2)
                    (0 until length * 2).forEach{
                        definition.field3300!![it] = buffer.short.toInt()
                    }
                    buffer.int
                    val subLength: Int = buffer.get().toInt() and 0xff
                    definition.field3292 = IntArray(subLength)
                    (0 until subLength).forEach{
                        definition.field3292!![it] = buffer.int
                    }
                    definition.field3309 = ByteArray(length)
                    (0 until length).forEach{
                        definition.field3309!![it] = buffer.get()
                    }
                }
                17 -> definition.field3308 = ByteBufferExt.getString(buffer)
                18 -> buffer.short
                19 -> definition.field3297 = buffer.short.toInt() and 0xffff
                21 -> buffer.int
                22 -> buffer.int
                23 -> ByteBufferExt.getMedium(buffer)
                24 -> buffer.int
                25 -> buffer.short
                28 -> buffer.get()
                29 -> buffer.get()
                30 -> buffer.get()
                0 -> break
                else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
            }
        } while (true)
        return definition
    }

}
