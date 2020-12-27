package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.ObjectDefinition
import com.osedit.spring.repository.ObjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class ObjectService(@Autowired private val objectRepository: ObjectRepository) {

    @PostConstruct
    fun post() {
        with(objectRepository) {
            saveAll(Library.objects()?.asIterable() as MutableIterable<ObjectDefinition>)
        }
    }

    fun objects(): List<ObjectDefinition> {
        return with(objectRepository) {
            findAll()
        }
    }

    fun objectById(id: Int): Optional<ObjectDefinition> {
        return with(objectRepository) {
            findById(id)
        }
    }

}