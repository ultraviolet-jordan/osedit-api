package com.osedit.spring.domain

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ItemDefinition(@Id val id: Int? = 0) : Definition {
    var name: String = "null"
    var resizeX: Int = 128
    var resizeY: Int = 128
    var resizeZ: Int = 128
    var xan2d: Int = 0
    var yan2d: Int = 0
    var zan2d: Int = 0
    var cost: Int = 1
    var isTradeable: Boolean = false
    var stackable: Int = 0
    var inventoryModel: Int = 0
    var members: Boolean = false
    var colorFind: ShortArray? = null
    var colorReplace: ShortArray? = null
    var textureFind: ShortArray? = null
    var textureReplace: ShortArray? = null
    var zoom2d: Int = 2000
    var xOffset2d: Int = 0
    var yOffset2d: Int = 0
    var ambient: Int = 0
    var contrast: Int = 0
    var countCo: IntArray? = null
    var countObj: IntArray? = null
    @ElementCollection
    var options = mutableListOf(null, null, "Take", null, "Drop")
    @ElementCollection
    var interfaceOptions = mutableListOf(null, null, null, null, "Drop")
    var maleModel0: Int = -1
    var maleModel1: Int = -1
    var maleModel2: Int = -1
    var maleOffset: Int = 0
    var maleHeadModel: Int = -1
    var maleHeadModel2: Int = -1
    var femaleModel0: Int = -1
    var femaleModel1: Int = -1
    var femaleModel2: Int = -1
    var femaleOffset: Int = -1
    var femaleHeadModel: Int = -1
    var femaleHeadModel2: Int = -1
    var notedID: Int = -1
    var notedTemplate: Int = -1
    var team: Int = 0
    var shiftClickDropIndex: Int = -2
    var boughtId: Int = -1
    var boughtTemplateId: Int = -1
    var placeholderId: Int = -1
    var placeholderTemplateId: Int = -1
    @ElementCollection
    var params = mutableMapOf<Int, String>()
}