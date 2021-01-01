package com.osedit.cache.provider

import com.osedit.cache.util.ByteBufferExt
import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.ItemDefinition
import com.osedit.spring.domain.ObjectDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer
import java.util.stream.IntStream

object ObjectProvider : Provider<ObjectDefinition> {

    private val log: Logger = LoggerFactory.getLogger(ObjectProvider::class.java)

    override fun decode(buffer: ByteBuffer, definition: ObjectDefinition): Definition {

        do when (val opcode: Int = buffer.get().toInt() and 0xff) {
            1 -> {
                val length: Int = buffer.get().toInt() and 0xff
                when {
                    length > 0 -> {
                        definition.objectTypes = IntArray(length)
                        definition.objectModels = IntArray(length)
                        (0 until length).forEach {
                            definition.objectModels!![it] = buffer.short.toInt() and 0xffff
                            definition.objectTypes!![it] = buffer.get().toInt() and 0xff
                        }
                    }
                }
            }
            2 -> definition.name = ByteBufferExt.getString(buffer)
            5 -> {
                val length: Int = buffer.get().toInt() and 0xff
                when {
                    length > 0 -> {
                        definition.objectTypes = null
                        definition.objectModels = IntStream.range(0, length).map {
                            buffer.short.toInt() and 0xffff
                        }.toArray()
                    }
                }
            }
            14 -> definition.sizeX = buffer.get().toInt() and 0xff
            15 -> definition.sizeY = buffer.get().toInt() and 0xff
            17 -> {
                definition.interactType = 0
                definition.blocksProjectile = false
            }
            18 -> definition.blocksProjectile = false
            19 -> definition.wallOrDoor = buffer.get().toInt() and 0xff
            21 -> definition.contouredGround = 0
            22 -> definition.mergeNormals = true
            23 -> definition.aBool2111 = true
            24 -> {
                definition.animationId = buffer.short.toInt() and 0xffff
                if (definition.animationId == 65535) {
                    definition.animationId = -1
                }
            }
            27 -> definition.interactType = 1
            28 -> definition.decorDisplacement = buffer.get().toInt() and 0xff
            29 -> definition.ambient = buffer.get().toInt()
            39 -> definition.contrast = buffer.get().toInt() * 25
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
                    definition.recolorToFind!![it] = buffer.short
                    definition.recolorToReplace!![it] = buffer.short
                }
            }
            41 -> {
                val length: Int = buffer.get().toInt() and 0xff
                definition.retextureToFind = ShortArray(length)
                definition.retextureToReplace = ShortArray(length)
                (0 until length).forEach {
                    definition.retextureToFind!![it] = buffer.short
                    definition.retextureToReplace!![it] = buffer.short
                }
            }
            62 -> definition.isRotated = true
            64 -> definition.shadow = false
            65 -> definition.modelSizeX = buffer.short.toInt() and 0xffff
            66 -> definition.modelSizeHeight = buffer.short.toInt() and 0xffff
            67 -> definition.modelSizeY = buffer.short.toInt() and 0xffff
            68 -> definition.mapSceneID = buffer.short.toInt() and 0xffff
            69 -> definition.blockingMask = buffer.get().toInt()
            70 -> definition.offsetX = buffer.short.toInt() and 0xffff
            71 -> definition.offsetHeight = buffer.short.toInt() and 0xffff
            72 -> definition.offsetY = buffer.short.toInt() and 0xffff
            73 -> definition.obstructsGround = true
            74 -> definition.isHollow = true
            75 -> definition.supportsItems = buffer.get().toInt() and 0xff
            77 -> {
                var varbitId: Int = buffer.short.toInt() and 0xffff
                if (varbitId == 65535) {
                    varbitId = -1
                }
                definition.varbitId = varbitId
                var varpId: Int = buffer.short.toInt() and 0xffff
                if (varpId == 65535) {
                    varpId = -1
                }
                definition.varpId = varpId
                val length: Int = buffer.get().toInt() and 0xff
                val configChangeDest = IntArray(length + 2)
                IntStream.rangeClosed(0, length).forEach {
                    configChangeDest[it] = buffer.short.toInt() and 0xffff
                    when {
                        configChangeDest[it] == 65535 -> {
                            configChangeDest[it] = -1
                        }
                    }
                }
                configChangeDest[length + 1] = -1
                definition.configChangeDest = configChangeDest
            }
            78 -> {
                definition.ambientSoundId = buffer.short.toInt() and 0xffff
                definition.anInt2083 = buffer.get().toInt() and 0xff
            }
            79 -> {
                definition.anInt2112 = buffer.short.toInt() and 0xffff
                definition.anInt2113 = buffer.short.toInt() and 0xffff
                definition.anInt2083 = buffer.get().toInt() and 0xff
                val length: Int = buffer.get().toInt() and 0xff
                definition.anIntArray2084 = IntStream.range(0, length).map {
                    buffer.short.toInt() and 0xffff
                }.toArray()
            }
            81 -> definition.contouredGround = (buffer.get().toInt() and 0xff) * 256
            82 -> definition.mapAreaId = buffer.short.toInt() and 0xffff
            92 -> {
                var varbitId: Int = buffer.short.toInt() and 0xffff
                if (varbitId == 65535) {
                    varbitId = -1
                }
                definition.varbitId = varbitId
                var varpId: Int = buffer.short.toInt() and 0xffff
                if (varpId == 65535) {
                    varpId = -1
                }
                definition.varpId = varpId
                var value: Int = buffer.short.toInt() and 0xffff
                if (value == 65535) {
                    value = -1
                }
                val length: Int = buffer.get().toInt() and 0xff
                val configChangeDest = IntArray(length + 2)
                IntStream.rangeClosed(0, length).forEach {
                    configChangeDest[it] = buffer.short.toInt() and 0xffff
                    when {
                        configChangeDest[it] == 65535 -> {
                            configChangeDest[it] = -1
                        }
                    }
                }
                configChangeDest[length + 1] = value
                definition.configChangeDest = configChangeDest
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
        while (true)
        return definition
    }

}
