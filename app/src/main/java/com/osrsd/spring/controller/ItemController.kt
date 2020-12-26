package com.osrsd.spring.controller

import com.osrsd.spring.domain.ItemDefinition
import com.osrsd.spring.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("items")
class ItemController(@Autowired val itemService: ItemService) {

    @GetMapping
    fun getItems(): ResponseEntity<List<ItemDefinition>> {
        return ResponseEntity.ok(itemService.getAllItems())
    }

    @GetMapping("/{itemId}")
    fun getItem(@PathVariable itemId: Int): ResponseEntity<ItemDefinition> {
        return itemService.getItemById(itemId).map { definition ->
            ResponseEntity.ok(definition)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }

}