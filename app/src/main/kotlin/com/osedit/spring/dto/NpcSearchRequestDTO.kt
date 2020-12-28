package com.osedit.spring.dto


data class NpcSearchRequestDTO(val searchText: String? = null, val skip: Int = 0, val take: Int = 50)