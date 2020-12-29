package com.osedit.cache.provider

import com.osedit.cache.MapConfig
import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.MapDefinition
import com.osedit.spring.domain.MapTileDefinition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

object MapProvider {

    fun decode(buffer: ByteBuffer, definition: MapDefinition): Definition? {
        var tiles: Array<Array<Array<MapTileDefinition?>>> = definition.tiles;

        for (z in 0 until MapConfig.Z) {
            for (x in 0 until MapConfig.X) {
                for (y in 0 until MapConfig.Y) {
                    val tile = MapTileDefinition()

                    while (true) {
                        when (val opcode: Int = buffer.get().toInt() and 0xff) {
                            0 -> break
                            1 -> {
                                tile.height = buffer.get().toInt() and 0xff
                                break
                            }
                            in 0..49 -> {
                                tile.attrOpcode = opcode
                                tile.overlayId = buffer.get().toInt()
                                tile.overlayPath = (((opcode - 2) / 4).toByte())
                                tile.overlayRotation = ((opcode - 2 and 3).toByte())
                            }
                            in 0..81 -> {
                                tile.settings = ((opcode - 49).toByte())
                            }
                            else -> tile.underlayId = ((opcode - 81).toByte())
                        }
                    }

                    tiles[z][x][y] = tile
                }
            }
        }
        return definition
    }
}