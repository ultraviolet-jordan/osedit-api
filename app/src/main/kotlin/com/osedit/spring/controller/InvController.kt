package com.osedit.spring.controller

import com.osedit.spring.domain.InvDefinition
import com.osedit.spring.service.InvService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("invs")
class InvController(@Autowired private val invService: InvService) {

    @GetMapping
    fun getInvs(): ResponseEntity<List<InvDefinition>> {
        return ResponseEntity.ok(invService.invs())
    }

    @GetMapping("/{invId}")
    fun getInv(@PathVariable invId: Int): ResponseEntity<InvDefinition> {
        return invService.invById(invId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}