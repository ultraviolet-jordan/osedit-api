package com.osedit.spring.domain

import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class EnumDefinition(@Id val id: Int? = 0): Definition {
    var keyType: Char? = null
    var valType: Char? = null
    var defaultString: String = "null"
    var defaultInt: Int = 0
    @ElementCollection
    @Column(length = 1268)
    var params = mutableMapOf<Long, String>()
}