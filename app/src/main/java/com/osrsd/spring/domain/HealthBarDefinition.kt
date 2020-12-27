package com.osrsd.spring.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class HealthBarDefinition(@Id private val id: Int? = 0) : Definition {
    var field3276: Int = 0
    var field3277: Int = 255
    var field3278: Int = 255
    var field3283: Int = -1
    var field3272: Int = 1
    var field3275: Int = 70
    var healthBarFrontSpriteId: Int = -1
    var healthBarBackSpriteId: Int = -1
    var healthScale: Int = 30
    var healthBarPadding: Int = 0
}
