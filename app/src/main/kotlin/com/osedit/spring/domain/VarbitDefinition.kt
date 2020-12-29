package com.osedit.spring.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class VarbitDefinition(@Id val id: Int? = 0) : Definition {
    var index: Int = 0
    var leastSignificantBit: Int = 0
    var mostSignificantBit: Int = 0
}