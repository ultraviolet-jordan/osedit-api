package com.osedit.spring.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class InvDefinition(@Id val id: Int? = 0) : Definition {
    var size: Int = 0
}