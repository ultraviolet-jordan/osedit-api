package com.osedit.spring.controller

import com.osedit.spring.domain.ObjectDefinition
import com.osedit.spring.service.ObjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("objects")
class ObjectController(@Autowired private val objectService: ObjectService) {

    @GetMapping
    fun getObjects(): ResponseEntity<List<ObjectDefinition>> {
        return ResponseEntity.ok(objectService.objects())
    }

    @GetMapping("/{objectId}")
    fun getObject(@PathVariable objectId: Int): ResponseEntity<ObjectDefinition> {
        return objectService.objectById(objectId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}