package com.osedit.spring.repository

import com.osedit.spring.domain.SpotAnimationDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpotAnimationRepository : JpaRepository<SpotAnimationDefinition, Int>