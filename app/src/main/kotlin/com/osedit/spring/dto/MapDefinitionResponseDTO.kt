package com.osedit.spring.dto

import com.osedit.spring.domain.MapTileDefinition

data class MapDefinitionResponseDTO(
    val regionX: Int?,
    val regionY: Int?,
    val plane: Int?,
    val data: Array<Array<MapTileDefinition?>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MapDefinitionResponseDTO

        if (regionX != other.regionX) return false
        if (regionY != other.regionY) return false
        if (plane != other.plane) return false
        if (!data.contentDeepEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = regionX ?: 0
        result = 31 * result + (regionY ?: 0)
        result = 31 * result + (plane ?: 0)
        result = 31 * result + data.contentDeepHashCode()
        return result
    }
}