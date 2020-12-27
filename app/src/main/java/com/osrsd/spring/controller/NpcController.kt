package com.osrsd.spring.controller

import com.osrsd.spring.domain.NpcDefinition
import com.osrsd.spring.dto.NPCSearchRequestDTO
import com.osrsd.spring.service.NpcService
import org.springframework.beans.factory.annotation.Autowired
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
    fun search(@RequestBody body: NPCSearchRequestDTO): ResponseEntity<List<NpcDefinition>> {
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