package com.osedit.spring.repository

import com.osedit.spring.domain.InvDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvRepository : JpaRepository<InvDefinition, Int>