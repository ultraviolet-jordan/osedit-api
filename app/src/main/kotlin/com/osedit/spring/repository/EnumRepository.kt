package com.osedit.spring.repository

import com.osedit.spring.domain.EnumDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnumRepository : JpaRepository<EnumDefinition, Int>