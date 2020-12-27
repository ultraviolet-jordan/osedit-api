package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.HealthBarDefinition
import com.osedit.spring.repository.HealthBarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class HealthBarService(@Autowired private val healthBarRepository: HealthBarRepository) {

    @PostConstruct
    fun post() {
        with(healthBarRepository) {
            saveAll(Library.healthBars()?.asIterable() as MutableIterable<HealthBarDefinition>)
        }
    }

    fun healthBars(): List<HealthBarDefinition> {
        return with(healthBarRepository) {
            findAll()
        }
    }

    fun healthBarById(id: Int): Optional<HealthBarDefinition> {
        return with(healthBarRepository) {
            findById(id)
        }
    }

}