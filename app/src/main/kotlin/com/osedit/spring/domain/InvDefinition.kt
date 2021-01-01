package com.osedit.spring.domain

import com.osedit.cache.Config
import com.osedit.cache.Indices
import com.osedit.cache.provider.InvProvider
import com.osedit.cache.provider.InvProvider.decode
import java.nio.ByteBuffer
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class InvDefinition(@Id private val id: Int? = 0) : Definition {
    var size: Int = 0

    override fun path(): String {
        return "/invs"
    }

    override fun config(): Config {
        return Config.INVS
    }

    override fun indices(): Indices {
        return Indices.CONFIG
    }

    override fun decode(byteBuffer: ByteBuffer): Definition {
        return InvProvider.decode(byteBuffer, this)
    }
}