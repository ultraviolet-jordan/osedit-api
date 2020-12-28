package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.NpcDefinition
import com.osedit.spring.dto.NpcSearchRequestDTO
import com.osedit.spring.repository.NpcRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
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

    fun searchNpcs(request: NpcSearchRequestDTO): Page<NpcDefinition>? {
        return if (request.searchText == null) {
            with(npcRepository) {
                findAll(PageRequest.of(request.skip, request.take))
            }
        } else {
            with(npcRepository) {
                searchNpcs(request.searchText, PageRequest.of(request.skip, request.take))
            }
        }
    }
}