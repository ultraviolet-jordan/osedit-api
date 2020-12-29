package com.osedit.spring.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.osedit.cache.MapConfig
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class MapDefinition(@Id val id: Int? = 0) : Definition {
    var regionX = 0
    var regionY = 0

    @JsonInclude
    @Transient
    var tiles: Array<Array<Array<MapTileDefinition?>>> = Array(MapConfig.Z) { Array(MapConfig.X) { Array(MapConfig.Y)  { null } } }
}