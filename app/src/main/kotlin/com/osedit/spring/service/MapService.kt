package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.MapDefinition
import com.osedit.spring.repository.MapDefinitionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.*
import javax.annotation.PostConstruct
import javax.imageio.ImageIO

@Service
class MapService(@Autowired private val mapDefinitionRepository: MapDefinitionRepository) {

    @PostConstruct
    fun post() {
        with(mapDefinitionRepository) {
            saveAll(Library.maps()?.asIterable() as MutableIterable<MapDefinition>)
        }
    }

    fun maps(): List<MapDefinition> {
        return with(mapDefinitionRepository) {
            findAll()
        }
    }

    fun mapById(id: Int): Optional<MapDefinition> {
        return with(mapDefinitionRepository) {
            findById(id)
        }
    }

    fun generateRegionAsPNG(regionId: Int, width: Int, height: Int): Optional<ByteArray>? {
        return with(mapDefinitionRepository) {
            findById(regionId)
        }.map {
            val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

            val bos = ByteArrayOutputStream()
            ImageIO.write(image, "png", bos)
            bos.toByteArray()
        }
    }
}