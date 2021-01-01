package com.osedit.cache.provider

import com.osedit.cache.util.ByteBufferExt
import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.SequenceDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object SequenceProvider : Provider<SequenceDefinition> {

    private val log: Logger = LoggerFactory.getLogger(SequenceProvider::class.java)

    override fun decode(buffer: ByteBuffer, definition: SequenceDefinition): Definition {
        do {
            when (val opcode: Int = buffer.get().toInt() and 0xff) {
                1 -> {
                    val length: Int = buffer.short.toInt() and 0xffff
                    definition.frameLengths = IntArray(length)
                    (0 until length).forEach {
                        definition.frameLengths!![it] = buffer.short.toInt() and 0xffff
                    }
                    definition.frameIDs = IntArray(length)
                    (0 until length).forEach {
                        definition.frameIDs!![it] = buffer.short.toInt() and 0xffff
                    }
                    (0 until length).forEach {
                        definition.frameIDs!![it] += (buffer.short.toInt() and 0xffff) shl 16
                    }
                }
                2 -> definition.frameStep = buffer.short.toInt() and 0xffff
                3 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.interleaveLeave = IntArray(length + 1)
                    (0 until length).forEach {
                        definition.interleaveLeave!![it] = buffer.get().toInt() and 0xff
                    }
                    definition.interleaveLeave!![length] = 9_999_999
                }
                4 -> definition.stretches = true
                5 -> definition.forcedPriority = buffer.get().toInt() and 0xff
                6 -> definition.leftHandItem = buffer.short.toInt() and 0xffff
                7 -> definition.rightHandItem = buffer.short.toInt() and 0xffff
                8 -> definition.maxLoops = buffer.get().toInt() and 0xff
                9 -> definition.precedenceAnimating = buffer.get().toInt() and 0xff
                10 -> definition.priority = buffer.get().toInt() and 0xff
                11 -> definition.replyMode = buffer.get().toInt() and 0xff
                12 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.chatFrameIds = IntArray(length)
                    (0 until length).forEach {
                        definition.chatFrameIds!![it] = buffer.short.toInt() and 0xffff
                    }
                    (0 until length).forEach {
                        definition.chatFrameIds!![it] += (buffer.short.toInt() and 0xffff) shl 16
                    }
                }
                13 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.frameSounds = IntArray(length)
                    (0 until length).forEach {
                        definition.frameSounds!![it] = ByteBufferExt.getMedium(buffer)
                    }
                }
                0 -> break
                else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
            }
        } while (true)
        return definition
    }

}
