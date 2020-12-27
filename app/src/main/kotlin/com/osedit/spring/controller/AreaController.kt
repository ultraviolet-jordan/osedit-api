package com.osedit.spring.controller

import com.osedit.spring.domain.AreaDefinition
import com.osedit.spring.service.AreaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("areas")
class AreaController(@Autowired private val areaService: AreaService) {

    @GetMapping
    fun getAreas(): ResponseEntity<List<AreaDefinition>> {
        return ResponseEntity.ok(areaService.areas())
    }

    @GetMapping("/{areaId}")
    fun getArea(@PathVariable areaId: Int): ResponseEntity<AreaDefinition> {
        return areaService.areaById(areaId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}