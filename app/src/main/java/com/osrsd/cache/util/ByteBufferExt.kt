package com.osrsd.cache.util

import java.nio.ByteBuffer

object ByteBufferExt {

    fun getMedium(buffer: ByteBuffer): Int {
        return buffer.get().toInt() and 0xff shl 16 or (buffer.get().toInt() and 0xff shl 8) or (buffer.get().toInt() and 0xff)
    }

    fun getString(buffer: ByteBuffer): String {
        val builder = StringBuilder()
        var b: Int
        while ((buffer.get().toInt() and 0xff).also { b = it } != 0) {
            builder.append(b.toChar())
        }
        return builder.toString()
    }

    fun getStringOrNull(buffer: ByteBuffer): String {
        val peek: Int = buffer[buffer.position()].toInt() and 0xff
        return if (peek != 0) {
            getString(buffer)
        } else {
            buffer.get() // discard
            "null"
        }
    }

    fun getShortSmart(buffer: ByteBuffer): Int {
        val peek: Int = buffer[buffer.position()].toInt() and 0xff
        return if (peek < 128) (buffer.get().toInt() and 0xff) - 64 else (buffer.short.toInt() and 0xffff) - 0xc000
    }

    fun getUnsignedShortSmart(buffer: ByteBuffer): Int {
        val peek: Int = buffer[buffer.position()].toInt() and 0xff
        return if (peek < 128) buffer.get().toInt() and 0xff else (buffer.short.toInt() and 0xffff) - 0x8000
    }

}