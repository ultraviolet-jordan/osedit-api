package com.osrsd.cache.util

import com.osrsd.spring.domain.Definition
import com.osrsd.cache.loader.Loader

data class Serializable(
        val loader: Loader,
        val definitions: List<Definition>,
        val path: String,
)