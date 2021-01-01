package com.osedit.cache.provider

import com.osedit.cache.util.ByteBufferExt
import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.ParamDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object ParamProvider : Provider<ParamDefinition> {

    private val log: Logger = LoggerFactory.getLogger(ParamProvider::class.java)

   override fun decode(buffer: ByteBuffer, definition: ParamDefinition): Definition {
        do {
            when (val opcode: Int = buffer.get().toInt() and 0xff) {
                1 -> definition.type = (buffer.get().toInt() and 0xff).toChar()
                2 -> definition.defaultInt = buffer.int
                4 -> definition.isMembers = false
                5 -> definition.defaultString = ByteBufferExt.getString(buffer)
                0 -> break
                else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
            }
        } while (true)
        return definition
    }

}
