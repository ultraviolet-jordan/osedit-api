package com.osedit.spring.domain

import com.osedit.cache.Config
import com.osedit.cache.Indices
import com.osedit.cache.provider.EnumProvider
import com.osedit.cache.provider.HealthBarProvider
import java.nio.ByteBuffer
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class EnumDefinition(@Id private val id: Int? = 0): Definition {
    var keyType: Char? = null
    var valType: Char? = null
    var defaultString: String = "null"
    var defaultInt: Int = 0
    @ElementCollection
    @Column(length = 1268)
    var params = mutableMapOf<Long, String>()

    override fun path(): String {
        return "/enums"
    }

    override fun config(): Config {
        return Config.ENUMS
    }

    override fun indices(): Indices {
        return Indices.CONFIG
    }

    override fun decode(byteBuffer: ByteBuffer): Definition {
       return EnumProvider.decode(byteBuffer, this)
    }
}