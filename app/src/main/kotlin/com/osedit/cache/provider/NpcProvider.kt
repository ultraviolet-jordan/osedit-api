package com.osedit.cache.provider

import com.osedit.cache.util.ByteBufferExt
import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.NpcDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object NpcProvider : Provider<NpcDefinition> {

    private val log: Logger = LoggerFactory.getLogger(NpcProvider::class.java)

    override fun decode(buffer: ByteBuffer, definition: NpcDefinition): Definition {
        do {
            when (val opcode: Int = buffer.get().toInt() and 0xff) {
                1 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.models = IntArray(length)
                    (0 until length).forEach {
                        definition.models!![it] = buffer.short.toInt() and 0xffff
                    }
                }
                2 -> definition.name = ByteBufferExt.getString(buffer)
                12 -> definition.size = buffer.get().toInt() and 0xff
                13 -> definition.standingAnimation = buffer.short.toInt() and 0xffff
                14 -> definition.walkingAnimation = buffer.short.toInt() and 0xffff
                15 -> definition.rotateLeftAnimation = buffer.short.toInt() and 0xffff
                16 -> definition.rotateRightAnimation = buffer.short.toInt() and 0xffff
                17 -> {
                    definition.walkingAnimation = buffer.short.toInt() and 0xffff
                    definition.rotate180Animation = buffer.short.toInt() and 0xffff
                    definition.rotate90RightAnimation = buffer.short.toInt() and 0xffff
                    definition.rotate90LeftAnimation = buffer.short.toInt() and 0xffff
                }
                in 30..34 -> {
                    definition.actions[opcode - 30] = ByteBufferExt.getString(buffer)
                    if (definition.actions[opcode - 30].equals("Hidden", true)) {
                        definition.actions[opcode - 30] = null
                    }
                }
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
                    definition.retextureToFind = ShortArray(length)
                    definition.retextureToReplace = ShortArray(length)
                    (0 until length).forEach {
                        definition.retextureToFind!![it] = (buffer.short.toInt() and 0xffff).toShort()
                        definition.retextureToReplace!![it] = (buffer.short.toInt() and 0xffff).toShort()
                    }
                }
                60 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.chatheadModels = IntArray(length)
                    (0 until length).forEach {
                        definition.chatheadModels!![it] = buffer.short.toInt() and 0xffff
                    }
                }
                93 -> definition.isMinimapVisible = false
                95 -> definition.combatLevel = buffer.short.toInt() and 0xffff
                97 -> definition.widthScale = buffer.short.toInt() and 0xffff
                98 -> definition.heightScale = buffer.short.toInt() and 0xffff
                99 -> definition.hasRenderPriority = true
                100 -> definition.ambient = buffer.get().toInt()
                101 -> definition.contrast = buffer.get().toInt()
                102 -> definition.headIcon = buffer.short.toInt() and 0xffff
                103 -> definition.rotationSpeed = buffer.short.toInt() and 0xffff
                106 -> {
                    definition.varbitId = buffer.short.toInt() and 0xffff
                    if (definition.varbitId == 65535) {
                        definition.varbitId = -1
                    }
                    definition.varpIndex = buffer.short.toInt() and 0xffff
                    if (definition.varpIndex == 65535) {
                        definition.varpIndex = -1
                    }
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.configs = mutableListOf(length)
                    (0..length).forEach {
                        val value = buffer.short.toInt() and 0xffff
                        definition.configs!!.add(value);
                        if (definition.configs!![it].toChar() == '\uffff') {
                            definition.configs!![it] = -1
                        }
                    }
                    definition.configs!![length + 1] = -1
                }
                107 -> definition.isInteractable = false
                109 -> definition.rotationFlag = false
                111 -> definition.isPet = true
                118 -> {
                    definition.varbitId = buffer.short.toInt() and 0xffff
                    if (definition.varbitId == 65535) {
                        definition.varbitId = -1
                    }
                    definition.varpIndex = buffer.short.toInt() and 0xffff
                    if (definition.varpIndex == 65535) {
                        definition.varpIndex = -1
                    }
                    var i: Int = buffer.short.toInt() and 0xffff
                    if (i == 0xffff) {
                        i = -1
                    }
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.configs = mutableListOf(length - 1)
                    (0..length).forEach {
                        definition.configs!!.add(buffer.short.toInt() and 0xffff)
                        if (definition.configs!![it].toChar() == '\uffff') {
                            definition.configs!![it] = -1
                        }
                    }
                    definition.configs!![length + 1] = i
                }
                249 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    (0 until length).forEach { _ ->
                        val string: Boolean = (buffer.get().toInt() and 0xff) == 1
                        val key: Int = ByteBufferExt.getMedium(buffer)
                        val value: Any = if (string) {
                            ByteBufferExt.getString(buffer)
                        } else {
                            buffer.int
                        }
                        definition.params[key] = value.toString()
                    }
                }
                0 -> break
                else -> log.warn(String.format("Unhandled definition opcode with id: %s.", opcode))
            }
        } while (true);
        return definition
    }

}