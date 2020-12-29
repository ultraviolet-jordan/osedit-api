package com.osedit.spring.controller

import com.osedit.cache.Library
import com.osedit.spring.domain.MapDefinition
import com.osedit.spring.dto.MapDefinitionResponseDTO
import com.osedit.spring.service.MapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable


@RestController
@RequestMapping("maps")
class MapController @Autowired constructor(
    private val mapService: MapService
) {

    @GetMapping
    fun maps(): ResponseEntity<List<MapDefinition>> {
        return ResponseEntity.ok(Library.maps())
    }

    @GetMapping("/{regionId}")
    fun getMap(@PathVariable regionId: Int, @RequestParam(required = false, defaultValue = "0") plane: Int = 0): ResponseEntity<MapDefinitionResponseDTO> {
        val map = Library.maps()?.firstOrNull { it.id == regionId } ?: return  ResponseEntity.notFound().build()
        return ResponseEntity.ok(MapDefinitionResponseDTO(map.regionX, map.regionY, plane, map.tiles[plane]))
    }

    @GetMapping("/view/{regionId}", produces = [MediaType.IMAGE_PNG_VALUE])
    fun viewOverlay(@PathVariable("regionId") regionId: Int,
                    @RequestParam(required = false, defaultValue = "50") width: Int = 50,
                    @RequestParam(required = false, defaultValue = "50") height: Int = 50) : ResponseEntity<ByteArray>? {
        return mapService.generateRegionAsPNG(regionId, width, height)?.map {
            ResponseEntity.ok(it)
        }?.orElseGet {
            ResponseEntity.notFound().build()
        }
    }
}