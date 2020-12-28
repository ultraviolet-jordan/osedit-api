package com.osedit.spring.controller

import com.osedit.spring.domain.NpcDefinition
import com.osedit.spring.dto.NpcSearchRequestDTO
import com.osedit.spring.service.NpcService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("npcs")
class NpcController(@Autowired private val itemService: NpcService) {

    @GetMapping
    fun getItems(): ResponseEntity<List<NpcDefinition>> {
        return ResponseEntity.ok(itemService.npcs())
    }

    @PostMapping("/search")
    fun search(@RequestBody body: NpcSearchRequestDTO): ResponseEntity<Page<NpcDefinition>> {
        return ResponseEntity.ok(itemService.searchNpcs(body))
    }

    @GetMapping("/{npcId}")
    fun getItem(@PathVariable npcId: Int): ResponseEntity<NpcDefinition> {
        return itemService.npcById(npcId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}