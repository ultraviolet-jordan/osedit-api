package com.osrsd.spring.repository

import com.osrsd.spring.domain.HealthBarDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HealthBarRepository : JpaRepository<HealthBarDefinition, Int>