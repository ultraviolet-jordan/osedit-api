package com.osedit.spring.controller

import com.osedit.spring.domain.SequenceDefinition
import com.osedit.spring.service.SequenceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sequences")
class SequenceController(@Autowired private val sequenceService: SequenceService) {

    @GetMapping
    fun getSequences(): ResponseEntity<List<SequenceDefinition>> {
        return ResponseEntity.ok(sequenceService.sequences())
    }

    @GetMapping("/{sequenceId}")
    fun getSequence(@PathVariable sequenceId: Int): ResponseEntity<SequenceDefinition> {
        return sequenceService.sequenceById(sequenceId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}