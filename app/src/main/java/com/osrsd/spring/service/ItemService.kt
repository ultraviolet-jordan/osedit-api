package com.osrsd.spring.service

import com.osrsd.cache.Library
import com.osrsd.spring.domain.ItemDefinition
import com.osrsd.spring.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class ItemService(@Autowired val itemRepository: ItemRepository) {

    @PostConstruct
    fun post() {
        Library.items()?.forEach { definition ->
            with(itemRepository) {
                save(definition as ItemDefinition)
            }
        }
    }

    fun getAllItems(): List<ItemDefinition> {
        return with(itemRepository) {
            findAll()
        }
    }

    fun getItemById(itemId: Int): Optional<ItemDefinition> {
        return with(itemRepository) {
            findById(itemId)
        }
    }

}