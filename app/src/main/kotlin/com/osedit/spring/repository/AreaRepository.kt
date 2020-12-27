package com.osedit.spring.repository

import com.osedit.spring.domain.AreaDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AreaRepository : JpaRepository<AreaDefinition, Int>