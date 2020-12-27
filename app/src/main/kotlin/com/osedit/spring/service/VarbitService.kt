package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.VarbitDefinition
import com.osedit.spring.repository.VarbitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class VarbitService(@Autowired private val varbitRepository: VarbitRepository) {

    @PostConstruct
    fun post() {
        with(varbitRepository) {
            saveAll(Library.varbits()?.asIterable() as MutableIterable<VarbitDefinition>)
        }
    }

    fun varbits(): List<VarbitDefinition> {
        return with(varbitRepository) {
            findAll()
        }
    }

    fun varbitById(id: Int): Optional<VarbitDefinition> {
        return with(varbitRepository) {
            findById(id)
        }
    }

}