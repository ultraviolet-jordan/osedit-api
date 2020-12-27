package com.osrsd.spring.service

import com.osrsd.cache.Library
import com.osrsd.spring.domain.HealthBarDefinition
import com.osrsd.spring.repository.HealthBarRepository
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

    fun healthBarById(itemId: Int): Optional<HealthBarDefinition> {
        return with(healthBarRepository) {
            findById(itemId)
        }
    }

}