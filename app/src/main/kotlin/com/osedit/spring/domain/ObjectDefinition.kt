package com.osedit.spring.domain

import com.osedit.cache.Config
import com.osedit.cache.Indices
import com.osedit.cache.provider.ObjectProvider
import java.nio.ByteBuffer
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ObjectDefinition(@Id private val id: Int? = 0) : Definition {
    var retextureToFind: ShortArray? = null
    var decorDisplacement = 16
    var isHollow = false
    var name: String  = "null"
    var objectModels: IntArray? = null
    var objectTypes: IntArray? = null
    var recolorToFind: ShortArray? = null
    var mapAreaId: Int = -1
    var retextureToReplace: ShortArray? = null
    var sizeX: Int = 1
    var sizeY: Int = 1
    var anInt2083: Int = 0
    var anIntArray2084: IntArray? = null
    var offsetX: Int = 0
    var mergeNormals: Boolean = false
    var wallOrDoor: Int = -1
    var animationId: Int = -1
    var varbitId: Int = -1
    var ambient: Int = 0
    var contrast: Int = 0
    @ElementCollection
    var actions = mutableListOf<String?>(null, null, null, null, null)
    var interactType: Int = 2
    var mapSceneID: Int = -1
    var blockingMask: Int = 0
    var recolorToReplace: ShortArray? = null
    var shadow: Boolean = true
    var modelSizeX: Int = 128
    var modelSizeHeight: Int = 128
    var modelSizeY: Int = 128
    var offsetHeight: Int = 0
    var offsetY: Int = 0
    var obstructsGround: Boolean = false
    var contouredGround: Int = -1
    var supportsItems: Int = -1
    @Column(length = 1055)
    var configChangeDest: IntArray? = null
    var isRotated: Boolean = false
    var varpId: Int = -1
    var ambientSoundId: Int = -1
    var aBool2111: Boolean = false
    var anInt2112: Int = 0
    var anInt2113: Int = 0
    var blocksProjectile: Boolean = true
    @ElementCollection
    var params = mutableMapOf<Int, String>()

    override fun path(): String {
        return "/objects"
    }

    override fun config(): Config {
        return Config.OBJECTS
    }

    override fun indices(): Indices {
        return Indices.CONFIG
    }

    override fun decode(byteBuffer: ByteBuffer): Definition {
        return ObjectProvider.decode(byteBuffer, this)
    }
}