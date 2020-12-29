package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.MapArchiveKey
import com.osedit.spring.repository.MapArchiveKeyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class MapArchiveKeyService(@Autowired private val mapArchiveKeyRepository: MapArchiveKeyRepository) {

    @PostConstruct
    fun post() {
        with(mapArchiveKeyRepository) {
            saveAll(Library.mapArchiveKeys()?.asIterable() as MutableIterable<MapArchiveKey>)
        }
    }

    fun areas(): List<MapArchiveKey> {
        return with(mapArchiveKeyRepository) {
            findAll()
        }
    }

    fun areaById(id: Int): Optional<MapArchiveKey> {
        return with(mapArchiveKeyRepository) {
            findById(id)
        }
    }
}