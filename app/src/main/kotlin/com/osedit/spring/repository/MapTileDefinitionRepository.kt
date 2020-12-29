package com.osedit.spring.repository

import com.osedit.spring.domain.MapTileDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MapTileDefinitionRepository : JpaRepository<MapTileDefinition, Int>