package com.osedit.spring.domain

import com.osedit.cache.Config
import com.osedit.cache.Indices
import com.osedit.cache.provider.HealthBarProvider
import java.nio.ByteBuffer
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class HealthBarDefinition(@Id private val id: Int? = 0) : Definition {
    var field3276: Int = 0
    var field3277: Int = 255
    var field3278: Int = 255
    var field3283: Int = -1
    var field3272: Int = 1
    var field3275: Int = 70
    var healthBarFrontSpriteId: Int = -1
    var healthBarBackSpriteId: Int = -1
    var healthScale: Int = 30
    var healthBarPadding: Int = 0

    override fun path(): String {
        return "/healthbars"
    }

    override fun config(): Config {
        return Config.HEALTH_BARS
    }

    override fun indices(): Indices {
        return Indices.SCRIPTS
    }

    override fun decode(byteBuffer: ByteBuffer): Definition {
        return HealthBarProvider.decode(byteBuffer, this)
    }
}
