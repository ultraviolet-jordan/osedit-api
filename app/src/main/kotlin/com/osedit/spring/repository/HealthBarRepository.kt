package com.osedit.spring.repository

import com.osedit.spring.domain.HealthBarDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HealthBarRepository : JpaRepository<HealthBarDefinition, Int>