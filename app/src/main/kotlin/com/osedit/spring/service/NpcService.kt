package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.NpcDefinition
import com.osedit.spring.dto.NpcSearchRequestDTO
import com.osedit.spring.repository.NpcRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class NpcService(@Autowired private val npcRepository: NpcRepository) {

    @PostConstruct
    fun post() {
        with(npcRepository) {
            saveAll(Library.npcs()?.asIterable() as MutableIterable<NpcDefinition>)
        }
    }

    fun npcs(): List<NpcDefinition> {
        return with(npcRepository) {
            findAll()
        }
    }

    fun npcById(id: Int): Optional<NpcDefinition> {
        return with(npcRepository) {
            findById(id)
        }
    }

    fun searchNpcs(body: NpcSearchRequestDTO): List<NpcDefinition> {
        return with(npcRepository) {
            searchNpcs(body.searchText)
        }
    }
}