package com.osedit.spring.domain

import com.osedit.cache.Config
import com.osedit.cache.Indices
import java.nio.ByteBuffer

interface Definition {
    fun path() : String
    fun config() : Config
    fun indices() : Indices
    fun decode(byteBuffer: ByteBuffer) : Definition
}