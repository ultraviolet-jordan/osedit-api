package com.osrsd.spring.repository

import com.osrsd.spring.domain.EnumDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnumRepository : JpaRepository<EnumDefinition, Int>