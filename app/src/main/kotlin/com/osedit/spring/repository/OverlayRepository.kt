package com.osedit.spring.repository

import com.osedit.spring.domain.OverlayDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OverlayRepository : JpaRepository<OverlayDefinition, Int>