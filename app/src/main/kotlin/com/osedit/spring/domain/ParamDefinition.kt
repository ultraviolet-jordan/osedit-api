package com.osedit.spring.domain

import com.osedit.cache.Config
import com.osedit.cache.Indices
import com.osedit.cache.provider.ParamProvider
import java.nio.ByteBuffer
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ParamDefinition(@Id private val id: Int? = 0) : Definition {
    var type: Char? = null
    var isMembers: Boolean = true
    var defaultInt: Int = 0
    var defaultString: String? = null

    override fun path(): String {
        return "/params"
    }

    override fun config(): Config {
        return Config.PARAMS
    }

    override fun indices(): Indices {
        return Indices.CONFIG
    }

    override fun decode(byteBuffer: ByteBuffer): Definition {
        return ParamProvider.decode(byteBuffer, this)
    }
}