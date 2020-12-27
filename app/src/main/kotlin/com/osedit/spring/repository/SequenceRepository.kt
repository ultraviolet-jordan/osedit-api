package com.osedit.spring.repository

import com.osedit.spring.domain.SequenceDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SequenceRepository : JpaRepository<SequenceDefinition, Int>