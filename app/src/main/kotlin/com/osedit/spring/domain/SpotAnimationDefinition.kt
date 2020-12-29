package com.osedit.spring.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class SpotAnimationDefinition(@Id val id: Int? = 0) : Definition {
    var rotation: Int = 0
    var textureToReplace: ShortArray? = null
    var textureToFind: ShortArray? = null
    var resizeY: Int = 128
    var animationId: Int = -1
    var recolorToFind: ShortArray? = null
    var recolorToReplace: ShortArray? = null
    var resizeX: Int = 128
    var modelId: Int = 0
    var ambient: Int = 0
    var contrast: Int = 0
}