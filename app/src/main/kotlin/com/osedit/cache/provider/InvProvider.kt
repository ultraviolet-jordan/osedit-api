package com.osedit.cache.provider

import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.InvDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object InvProvider : Provider<InvDefinition> {

    private val log: Logger = LoggerFactory.getLogger(InvProvider::class.java)

    override fun decode(buffer: ByteBuffer, definition: InvDefinition): Definition {
        do {
            when (val opcode: Int = buffer.get().toInt() and 0xff) {
                2 -> definition.size = buffer.short.toInt() and 0xffff
                0 -> break
                else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
            }
        } while (true)
        return definition
    }
}
