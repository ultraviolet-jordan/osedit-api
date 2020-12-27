package com.osedit.spring.controller

import com.osedit.spring.domain.VarbitDefinition
import com.osedit.spring.service.VarbitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("varbits")
class VarbitController(@Autowired private val varbitService: VarbitService) {

    @GetMapping
    fun getVarbits(): ResponseEntity<List<VarbitDefinition>> {
        return ResponseEntity.ok(varbitService.varbits())
    }

    @GetMapping("/{varbitId}")
    fun getVarbit(@PathVariable varbitId: Int): ResponseEntity<VarbitDefinition> {
        return varbitService.varbitById(varbitId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}