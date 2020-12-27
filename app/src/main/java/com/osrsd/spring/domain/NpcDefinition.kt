package com.osrsd.spring.domain

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class NpcDefinition(@Id private val id: Int? = 0) : Definition {
    var name: String = "null"
    var size = 1
    var models: IntArray? = null
    var chatheadModels: IntArray? = null
    var standingAnimation = -1
    var rotateLeftAnimation = -1
    var rotateRightAnimation = -1
    var walkingAnimation = -1
    var rotate180Animation = -1
    var rotate90RightAnimation = -1
    var rotate90LeftAnimation = -1
    var recolorToFind: ShortArray? = null
    var recolorToReplace: ShortArray? = null
    var retextureToFind: ShortArray? = null
    var retextureToReplace: ShortArray? = null
    @ElementCollection
    var actions = mutableListOf<String?>(null, null, null, null, null)
    var isMinimapVisible = true
    var combatLevel = -1
    var widthScale = 128
    var heightScale = 128
    var hasRenderPriority = false
    var ambient = 0
    var contrast = 0
    var headIcon = -1
    var rotationSpeed = 32
    @ElementCollection
    var configs: MutableList<Int>? = null
    var varbitId = -1
    var varpIndex = -1
    var isInteractable = true
    var rotationFlag = true
    var isPet = false
    @ElementCollection
    var params = mutableMapOf<Int, String>()
}