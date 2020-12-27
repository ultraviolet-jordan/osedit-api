package com.osedit.cache.loader

import com.osedit.cache.util.Serializable

interface Loader {
    fun load(): Serializable
}