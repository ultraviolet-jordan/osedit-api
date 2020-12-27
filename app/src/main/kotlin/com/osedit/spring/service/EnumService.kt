package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.EnumDefinition
import com.osedit.spring.repository.EnumRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class EnumService(@Autowired private val enumRepository: EnumRepository) {

    @PostConstruct
    fun post() {
        with(enumRepository) {
            saveAll(Library.enums()?.asIterable() as MutableIterable<EnumDefinition>)
        }
    }

    fun enums(): List<EnumDefinition> {
        return with(enumRepository) {
            findAll()
        }
    }

    fun enumById(id: Int): Optional<EnumDefinition> {
        return with(enumRepository) {
            findById(id)
        }
    }

}