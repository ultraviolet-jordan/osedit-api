package com.osedit.spring.controller

import com.osedit.spring.domain.SpotAnimationDefinition
import com.osedit.spring.service.SpotAnimationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("spotanimations")
class SpotAnimationController(@Autowired private val spotAnimationService: SpotAnimationService) {

    @GetMapping
    fun getSpotAnimations(): ResponseEntity<List<SpotAnimationDefinition>> {
        return ResponseEntity.ok(spotAnimationService.spotAnimations())
    }

    @GetMapping("/{spotAnimationId}")
    fun getSpotAnimation(@PathVariable spotAnimationId: Int): ResponseEntity<SpotAnimationDefinition> {
        return spotAnimationService.spotAnimationById(spotAnimationId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}