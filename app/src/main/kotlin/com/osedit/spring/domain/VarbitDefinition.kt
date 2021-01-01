package com.osedit.spring.domain

import com.osedit.cache.Config
import com.osedit.cache.Indices
import com.osedit.cache.provider.VarbitProvider
import java.nio.ByteBuffer
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class VarbitDefinition(@Id private val id: Int? = 0) : Definition {
    var index: Int = 0
    var leastSignificantBit: Int = 0
    var mostSignificantBit: Int = 0

    override fun path(): String {
        return "/varbits"
    }

    override fun config(): Config {
        return Config.VARBITS
    }

    override fun indices(): Indices {
        return Indices.CONFIG
    }

    override fun decode(byteBuffer: ByteBuffer): Definition {
        return VarbitProvider.decode(byteBuffer, this)
    }
}