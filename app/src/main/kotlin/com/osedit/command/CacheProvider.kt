package com.osedit.command

import com.google.common.collect.ImmutableList
import com.osedit.cache.Library
import com.osedit.cache.loader.DefinitionLoader
import com.osedit.spring.App
import com.osedit.spring.domain.*
import kotlinx.coroutines.*

object CacheProvider {

    // Store definitions in an immutable list for later accessing
    private val DEFINITIONS = ImmutableList.of(
            AreaDefinition(),
            EnumDefinition(),
            HealthBarDefinition(),
            InvDefinition(),
            ItemDefinition(),
            NpcDefinition(),
            ObjectDefinition(),
            ParamDefinition(),
            SequenceDefinition(),
            SpotAnimationDefinition(),
            VarbitDefinition()
    )

    fun execute() {
        val start = System.currentTimeMillis()
        for (definition in DEFINITIONS) {
            store(definition, start)
        }
    }

    private fun store(definition: Definition, start: Long) {
        Library.store(definition::class.java, DefinitionLoader.load(definition))
        definition::class.simpleName?.let { App.prompt(it, start) };
    }
}

