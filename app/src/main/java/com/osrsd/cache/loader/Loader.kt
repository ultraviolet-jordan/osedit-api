package com.osrsd.cache.loader

import com.osrsd.cache.util.Serializable

interface Loader {
    fun load(): Serializable
}