package com.osedit.spring.domain

import com.osedit.cache.Config
import com.osedit.cache.Indices
import com.osedit.cache.provider.SpotAnimationProvider
import java.nio.ByteBuffer
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class SpotAnimationDefinition(@Id private val id: Int? = 0) : Definition {
    var rotation: Int = 0
    var textureToReplace: ShortArray? = null
    var textureToFind: ShortArray? = null
    var resizeY: Int = 128
    var animationId: Int = -1
    var recolorToFind: ShortArray? = null
    var recolorToReplace: ShortArray? = null
    var resizeX: Int = 128
    var modelId: Int = 0
    var ambient: Int = 0
    var contrast: Int = 0

    override fun path(): String {
        return "/spotanimations"
    }

    override fun config(): Config {
        return Config.SPOT_ANIMS
    }

    override fun indices(): Indices {
        return Indices.CONFIG
    }

    override fun decode(byteBuffer: ByteBuffer): Definition {
        return SpotAnimationProvider.decode(byteBuffer, this)
    }
}