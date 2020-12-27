package com.osedit.spring.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class SequenceDefinition(@Id private val id: Int? = 0) : Definition {
    @Column(length = 1191)
    var frameIDs: IntArray? = null
    @Column(length = 387)
    var chatFrameIds: IntArray? = null
    @Column(length = 1191)
    var frameLengths: IntArray? = null
    @Column(length = 523)
    var frameSounds: IntArray? = null
    var frameStep: Int = -1
    var interleaveLeave: IntArray? = null
    var stretches: Boolean = false
    var forcedPriority: Int = 5
    var leftHandItem: Int = -1
    var rightHandItem: Int = -1
    var maxLoops: Int = 99
    var precedenceAnimating: Int = -1
    var priority: Int = -1
    var replyMode: Int = 2
}