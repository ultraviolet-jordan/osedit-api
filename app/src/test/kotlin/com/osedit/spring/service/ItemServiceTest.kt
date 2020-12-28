package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.cache.loader.ItemLoader
import com.osedit.spring.App
import com.osedit.spring.domain.Definition
import com.osedit.spring.domain.ItemDefinition
import org.junit.Before
import org.junit.Test

internal class ItemServiceTest {

    @Before
    fun init() {
        App::class.java.getResourceAsStream(App.resource).use {
            App.properties.load(it)
        }
        Library.store(ItemDefinition::class.java, ItemLoader().load().definitions)
    }

    @Test
    fun test() {
        val definition: List<Definition>? = Library.definitions[ItemDefinition::class.java]
        assert(definition != null)
        definition?.forEach { assert(it is ItemDefinition) }
    }

}