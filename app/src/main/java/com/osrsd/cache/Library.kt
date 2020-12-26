package com.osrsd.cache

import com.displee.cache.CacheLibrary
import com.displee.cache.index.Index
import com.osrsd.cache.def.Definition
import com.osrsd.spring.App
import com.osrsd.spring.domain.ItemDefinition
import java.util.concurrent.ConcurrentHashMap

object Library {

    /**
     * The filesystem cache library.
     */
    private val cacheLibrary = CacheLibrary(App.baseDirectory())

    /**
     * Cached definitions provided from the cache library.
     */
    private val definitions: ConcurrentHashMap<Class<out Definition>, List<Definition>> = ConcurrentHashMap()

    /**
     * Stores a provided list of definitions.
     */
    fun store(clazz: Class<out Definition>, list: List<Definition>) {
        definitions[clazz] = list
    }

    /**
     * Gets the item definitions.
     */
    fun items(): List<Definition>? {
        return definitions[ItemDefinition::class.java]
    }

    /**
     * Gets the raw index from the cache library.
     * This is annotated with synchronized because of a weird issue with multiple threads accessing the library simultaneously.
     */
    @Synchronized
    fun index(indexId: Int): Index {
        val index: Index = cacheLibrary.index(indexId)
        index.cache()
        return index
    }

}