package com.osedit.spring.controller

import com.osedit.spring.domain.OverlayDefinition
import com.osedit.spring.service.OverlayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable


@RestController
@RequestMapping("overlays")
class OverlayController(@Autowired private val overlayService: OverlayService) {

    @GetMapping
    fun getOverlays(): ResponseEntity<List<OverlayDefinition>> {
        return ResponseEntity.ok(overlayService.overlays())
    }

    @GetMapping("/{overlayId}")
    fun getOverlay(@PathVariable overlayId: Int): ResponseEntity<OverlayDefinition> {
        return overlayService.overlayById(overlayId).map {
            ResponseEntity.ok(it)
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/view/{overlayId}", produces = [MediaType.IMAGE_PNG_VALUE])
    fun viewOverlay(@PathVariable("overlayId") overlayId: Int, @RequestParam(required = false, defaultValue = "50") width: Int = 50, @RequestParam(required = false, defaultValue = "50") height: Int = 50): ResponseEntity<ByteArray>? {
        return overlayService.getOverlayPNG(overlayId, width, height)?.map {
            ResponseEntity.ok(it)
        }?.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}