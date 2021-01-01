package com.osedit.spring.domain

import com.osedit.cache.Config
import com.osedit.cache.Indices
import com.osedit.cache.provider.AreaProvider
import java.nio.ByteBuffer
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class AreaDefinition(@Id @GeneratedValue(strategy = GenerationType.AUTO) private val id: Int? = 0): Definition {
    var field3292: IntArray? = null
    var spriteId: Int = -1
    var field3294: Int = -1
    var name: String? = null
    var tileHash = 0
    var field3297: Int = -1
    var field3298: Array<String?> = arrayOfNulls(5)
    var field3300: IntArray? = null
    var field3308: String? = null
    var field3309: ByteArray? = null
    var field3310 = 0

    override fun path() : String {
        return "/path"
    }

    override fun config(): Config {
        return Config.AREAS
    }

    override fun indices(): Indices {
        return Indices.CONFIG
    }

    override fun decode(byteBuffer: ByteBuffer): Definition {
        return AreaProvider.decode(byteBuffer, this)
    }

}