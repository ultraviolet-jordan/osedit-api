package com.osedit.spring.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ParamDefinition(@Id private val id: Int? = 0) : Definition {
    var type: Char? = null
    var isMembers: Boolean = true
    var defaultInt: Int = 0
    var defaultString: String? = null
}