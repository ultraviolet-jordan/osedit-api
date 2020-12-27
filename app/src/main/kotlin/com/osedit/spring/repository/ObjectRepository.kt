package com.osedit.spring.repository

import com.osedit.spring.domain.ObjectDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ObjectRepository : JpaRepository<ObjectDefinition, Int>