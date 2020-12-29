package com.osedit.spring.repository

import com.osedit.spring.domain.MapDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MapDefinitionRepository : JpaRepository<MapDefinition, Int>