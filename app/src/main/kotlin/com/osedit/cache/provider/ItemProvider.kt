package com.osedit.cache.provider

import com.osedit.cache.util.ByteBufferExt
import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.ItemDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object ItemProvider : Provider<ItemDefinition> {

    override fun decode(buffer: ByteBuffer, definition: ItemDefinition): Definition {
        val log: Logger = LoggerFactory.getLogger(ItemProvider::class.java)
        do {
            when (val opcode: Int = buffer.get().toInt() and 0xff) {
                1 -> definition.inventoryModel = buffer.short.toInt() and 0xffff
                2 -> definition.name = ByteBufferExt.getString(buffer)
                4 -> definition.zoom2d = buffer.short.toInt() and 0xffff
                5 -> definition.xan2d = buffer.short.toInt() and 0xffff
                6 -> definition.yan2d = buffer.short.toInt() and 0xffff
                7 -> {
                    definition.xOffset2d = buffer.short.toInt() and 0xffff
                    if (definition.xOffset2d > Short.MAX_VALUE) {
                        definition.xOffset2d -= 65536
                    }
                }
                8 -> {
                    definition.yOffset2d = buffer.short.toInt() and 0xffff
                    if (definition.yOffset2d > Short.MAX_VALUE) {
                        definition.yOffset2d -= 65536
                    }
                }
                11 -> definition.stackable = 1
                12 -> definition.cost = buffer.int
                16 -> definition.members = true
                23 -> {
                    definition.maleModel0 = buffer.short.toInt() and 0xffff
                    definition.maleOffset = buffer.get().toInt() and 0xff
                }
                24 -> definition.maleModel1 = buffer.short.toInt() and 0xffff
                25 -> {
                    definition.femaleModel0 = buffer.short.toInt() and 0xffff
                    definition.femaleOffset = buffer.get().toInt() and 0xff
                }
                26 -> definition.femaleModel1 = buffer.short.toInt() and 0xffff
                in 30..34 -> {
                    definition.options[opcode - 30] = ByteBufferExt.getString(buffer)
                    if (definition.options[opcode - 30].equals("Hidden", true)) {
                        definition.options[opcode - 30] = null
                    }
                }
                in 35..39 -> definition.interfaceOptions[opcode - 35] = ByteBufferExt.getString(buffer)
                40 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.colorFind = ShortArray(length)
                    definition.colorReplace = ShortArray(length)
                    (0 until length).forEach {
                        definition.colorFind!![it] = (buffer.short.toInt() and 0xffff).toShort()
                        definition.colorReplace!![it] = (buffer.short.toInt() and 0xffff).toShort()
                    }
                }
                41 -> {
                    val length: Int = buffer.get().toInt() and 0xff
                    definition.textureFind = ShortArray(length)
                    definition.textureReplace = ShortArray(length)
                    (0 until length).forEach {
                        definition.textureFind!![it] = (buffer.short.toInt() and 0xffff).toShort()
                        definition.textureReplace!![it] = (buffer.short.toInt() and 0xffff).toShort()
                    }
                }
                42 -> definition.shiftClickDropIndex = buffer.get().toInt()
                65 -> definition.isTradeable = true
                78 -> definition.maleModel2 = buffer.short.toInt() and 0xffff
                79 -> definition.femaleModel2 = buffer.short.toInt() and 0xffff
                90 -> definition.maleHeadModel = buffer.short.toInt() and 0xffff
                91 -> definition.femaleHeadModel = buffer.short.toInt() and 0xffff
                92 -> definition.maleHeadModel2 = buffer.short.toInt() and 0xffff
                93 -> definition.femaleHeadModel2 = buffer.short.toInt() and 0xffff
                95 -> definition.zan2d = buffer.short.toInt() and 0xffff
                97 -> definition.notedID = buffer.short.toInt() and 0xffff
                98 -> definition.notedTemplate = buffer.short.toInt() and 0xffff
                in 100..109 -> {
                    if (definition.countObj == null) {
                        definition.countObj = IntArray(10)
                        definition.countCo = IntArray(10)
                    }
                    definition.countObj!![opcode - 100] = buffer.short.toInt() and 0xffff
                    definition.countCo!![opcode - 100] = buffer.short.toInt() and 0xffff
                }
                110 -> definition.resizeX = buffer.short.toInt() and 0xffff
                111 -> definition.resizeY = buffer.short.toInt() and 0xffff
                112 -> definition.resizeZ = buffer.short.toInt() and 0xffff
                113 -> definition.ambient = buffer.get().toInt()
                114 -> definition.contrast = buffer.get().toInt()
                115 -> definition.team = buffer.get().toInt()
                139 -> definition.boughtId = buffer.short.toInt() and 0xffff
                140 -> definition.boughtTemplateId = buffer.short.toInt() and 0xffff
                148 -> definition.placeholderId = buffer.short.toInt() and 0xffff
                149 -> definition.placeholderTemplateId = buffer.short.toInt() and 0xffff
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
        } while (true)
        return definition
    }

}
