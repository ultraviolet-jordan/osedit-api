package com.osedit.cache.provider

import com.osedit.spring.domain.Definition
import java.nio.ByteBuffer

interface Provider<T : Definition> {
    fun decode(buffer: ByteBuffer, definition: T) : Definition
}