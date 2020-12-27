package com.osedit.spring.controller

import com.osedit.spring.domain.HealthBarDefinition
import com.osedit.spring.service.HealthBarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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