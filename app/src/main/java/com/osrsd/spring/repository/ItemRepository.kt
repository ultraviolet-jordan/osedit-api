package com.osrsd.spring.repository

import com.osrsd.spring.domain.ItemDefinition
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<ItemDefinition, Int>