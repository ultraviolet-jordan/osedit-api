package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.InvDefinition
import com.osedit.spring.domain.OverlayDefinition
import com.osedit.spring.repository.InvRepository
import com.osedit.spring.repository.OverlayRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.*
import javax.annotation.PostConstruct
import javax.imageio.ImageIO

@Service
class OverlayService(@Autowired private val overlayRepository: OverlayRepository) {

    @PostConstruct
    fun post() {
        with(overlayRepository) {
            saveAll(Library.overlays()?.asIterable() as MutableIterable<OverlayDefinition>)
        }
    }

    fun overlays(): List<OverlayDefinition> {
        return with(overlayRepository) {
            findAll()
        }
    }

    fun overlayById(id: Int): Optional<OverlayDefinition> {
        return with(overlayRepository) {
            findById(id)
        }
    }

    fun getOverlayPNG(overlayId: Int, width: Int, height: Int): Optional<ByteArray>? {
        return with(overlayRepository) {
            findById(overlayId)
        }.map {
            val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

            for (y in 0 until height) {
                for (x in 0 until width) {
                    var rgb: Int = it.rgbColor

                    if (rgb > 0) {
                        val red = rgb shr 16 and 0xFF
                        val green = rgb shr 8 and 0xFF
                        val blue = rgb and 0xFF
                        image.setRGB(x, y, Color(red, green, blue).rgb)
                    }

                    val secondaryRgb = it.secondaryRgbColor

                    if (secondaryRgb > 0) {
                        val red = secondaryRgb shr 16 and 0xFF
                        val green = secondaryRgb shr 8 and 0xFF
                        val blue = secondaryRgb and 0xFF
                        image.setRGB(x, y, Color(red, green, blue).rgb)
                    }
                 }
            }

            val bos = ByteArrayOutputStream()
            ImageIO.write(image, "png", bos)
            bos.toByteArray()
        }
    }
}