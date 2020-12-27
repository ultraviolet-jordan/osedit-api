package com.osrsd.spring.domain

import javax.persistence.*

@Entity
data class EnumDefinition(@Id private val id: Int? = 0) : Definition {
    var keyType: Char? = null
    var valType: Char? = null
    var defaultString: String = "null"
    var defaultInt: Int = 0
    @ElementCollection
    @Column(length = 1268)
    var params = mutableMapOf<Long, String>()
}