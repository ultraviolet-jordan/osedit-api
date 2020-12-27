package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.InvDefinition
import com.osedit.spring.repository.InvRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class InvService(@Autowired private val invRepository: InvRepository) {

    @PostConstruct
    fun post() {
        with(invRepository) {
            saveAll(Library.invs()?.asIterable() as MutableIterable<InvDefinition>)
        }
    }

    fun invs(): List<InvDefinition> {
        return with(invRepository) {
            findAll()
        }
    }

    fun invById(id: Int): Optional<InvDefinition> {
        return with(invRepository) {
            findById(id)
        }
    }

}