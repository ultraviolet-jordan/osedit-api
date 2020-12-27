package com.osrsd.spring.service

import com.osrsd.cache.Library
import com.osrsd.spring.domain.NpcDefinition
import com.osrsd.spring.dto.NPCSearchRequestDTO
import com.osrsd.spring.repository.NpcRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class NpcService(@Autowired private val npcRepository: NpcRepository) {

    @PostConstruct
    fun post() {
        with(npcRepository) {
            saveAll(Library.npcs())
        }
    }

    fun npcs(): List<NpcDefinition> {
        return with(npcRepository) {
            findAll()
        }
    }

    fun npcById(itemId: Int): Optional<NpcDefinition> {
        return with(npcRepository) {
            findById(itemId)
        }
    }

    fun searchNpcs(body: NPCSearchRequestDTO): List<NpcDefinition> {
        return with(npcRepository) {
            searchNpcs(body.searchText)
        }
    }
}