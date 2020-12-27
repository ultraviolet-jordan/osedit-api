package com.osrsd.spring.controller

import com.osrsd.spring.domain.HealthBarDefinition
import com.osrsd.spring.service.HealthBarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("healthbars")
class HealthBarController(@Autowired private val healthBarService: HealthBarService) {

    @GetMapping
    fun getHealthBars(): ResponseEntity<List<HealthBarDefinition>> {
        return ResponseEntity.ok(healthBarService.healthBars())
    }

    @GetMapping("/{barId}")
    fun getHealthBar(@PathVariable barId: Int): ResponseEntity<HealthBarDefinition> {
        return healthBarService.healthBarById(barId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}