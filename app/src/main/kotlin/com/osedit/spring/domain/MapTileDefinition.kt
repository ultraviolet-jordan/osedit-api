package com.osedit.spring.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class MapTileDefinition(@Id private val id: Int? = 0): Definition {
    var height: Int? = null
    var attrOpcode = 0
    var settings: Byte = 0
    var overlayId = 0
    var overlayPath: Byte = 0
    var overlayRotation: Byte = 0
    var underlayId: Byte = 0
}