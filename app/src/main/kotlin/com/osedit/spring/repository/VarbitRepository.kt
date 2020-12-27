package com.osedit.spring.repository

import com.osedit.spring.domain.VarbitDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VarbitRepository : JpaRepository<VarbitDefinition, Int>