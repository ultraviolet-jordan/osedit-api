package com.osedit.spring.controller

import com.osedit.spring.domain.ParamDefinition
import com.osedit.spring.service.ParamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("params")
class ParamController(@Autowired private val paramService: ParamService) {

    @GetMapping
    fun getParams(): ResponseEntity<List<ParamDefinition>> {
        return ResponseEntity.ok(paramService.params())
    }

    @GetMapping("/{paramId}")
    fun getParam(@PathVariable paramId: Int): ResponseEntity<ParamDefinition> {
        return paramService.paramById(paramId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}