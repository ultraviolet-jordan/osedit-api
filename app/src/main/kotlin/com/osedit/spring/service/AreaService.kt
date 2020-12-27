package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.AreaDefinition
import com.osedit.spring.repository.AreaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class AreaService(@Autowired private val areaRepository: AreaRepository) {

    @PostConstruct
    fun post() {
        with(areaRepository) {
            saveAll(Library.areas()?.asIterable() as MutableIterable<AreaDefinition>)
        }
    }

    fun areas(): List<AreaDefinition> {
        return with(areaRepository) {
            findAll()
        }
    }

    fun areaById(id: Int): Optional<AreaDefinition> {
        return with(areaRepository) {
            findById(id)
        }
    }

}