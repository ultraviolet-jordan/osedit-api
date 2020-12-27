package com.osedit.spring.repository

import com.osedit.spring.domain.ParamDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParamRepository : JpaRepository<ParamDefinition, Int>