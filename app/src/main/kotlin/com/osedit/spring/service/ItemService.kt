package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.ItemDefinition
import com.osedit.spring.dto.ItemSearchRequestDTO
import com.osedit.spring.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
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

    fun itemById(id: Int): Optional<ItemDefinition> {
        return with(itemRepository) {
            findById(id)
        }
    }

    fun searchItems(body: ItemSearchRequestDTO): List<ItemDefinition> {
        return with(itemRepository) {
            searchItems(body.searchText)
        }
    }
}