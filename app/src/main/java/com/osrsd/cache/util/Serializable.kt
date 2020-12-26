package com.osrsd.cache.util

import com.osrsd.cache.def.Definition
import com.osrsd.cache.loader.Loader

data class Serializable(
        val loader: Loader,
        val definitions: List<Definition>,
        val path: String,
)