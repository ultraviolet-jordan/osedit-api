package com.osrsd.spring.service

import com.osrsd.cache.Library
import com.osrsd.spring.domain.ItemDefinition
import com.osrsd.spring.dto.ItemSearchRequestDTO
import com.osrsd.spring.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import javax.annotation.PostConstruct

@Service
class ItemService(@Autowired private val itemRepository: ItemRepository) {

    @PostConstruct
    fun post() {
        with(itemRepository) {
            saveAll(Library.items()?.asIterable() as MutableIterable<ItemDefinition>)
        }
    }

    fun items(): List<ItemDefinition> {
        return with(itemRepository) {
            findAll()
        }
    }

    fun itemById(itemId: Int): Optional<ItemDefinition> {
        return with(itemRepository) {
            findById(itemId)
        }
    }

    fun searchItems(body: ItemSearchRequestDTO): List<ItemDefinition> {
        return with(itemRepository) {
            searchItems(body.searchText)
        }
    }
}