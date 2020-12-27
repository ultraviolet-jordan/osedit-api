package com.osedit.spring.controller

import com.osedit.spring.domain.ItemDefinition
import com.osedit.spring.dto.ItemSearchRequestDTO
import com.osedit.spring.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("items")
class ItemController(@Autowired private val itemService: ItemService) {

    @GetMapping
    fun getItems(): ResponseEntity<List<ItemDefinition>> {
        return ResponseEntity.ok(itemService.items())
    }

    @PostMapping("/search")
    fun search(@RequestBody body: ItemSearchRequestDTO): ResponseEntity<List<ItemDefinition>> {
        return ResponseEntity.ok(itemService.searchItems(body))
    }

    @GetMapping("/{itemId}")
    fun getItem(@PathVariable itemId: Int): ResponseEntity<ItemDefinition> {
        return itemService.itemById(itemId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}