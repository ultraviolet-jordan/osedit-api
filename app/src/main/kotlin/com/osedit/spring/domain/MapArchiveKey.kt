package com.osedit.spring.domain

import com.google.gson.annotations.SerializedName
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class MapArchiveKey(@Id @SerializedName("mapsquare") val id: Int = 0): Definition {
    var key: IntArray? = null
}