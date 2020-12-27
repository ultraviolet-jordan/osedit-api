package com.osedit.spring.controller

import com.osedit.spring.domain.EnumDefinition
import com.osedit.spring.service.EnumService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("enums")
class EnumController(@Autowired private val enumService: EnumService) {

    @GetMapping
    fun getEnums(): ResponseEntity<List<EnumDefinition>> {
        return ResponseEntity.ok(enumService.enums())
    }

    @GetMapping("/{enumId}")
    fun getEnum(@PathVariable enumId: Int): ResponseEntity<EnumDefinition> {
        return enumService.enumById(enumId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}