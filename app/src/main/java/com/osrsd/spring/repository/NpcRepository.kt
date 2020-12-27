package com.osrsd.spring.repository

import com.osrsd.spring.domain.NpcDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NpcRepository : JpaRepository<NpcDefinition, Int> {
    @Query("SELECT def FROM NpcDefinition def WHERE name IN (:searchText) OR " +
            ":searchText IN ELEMENTS(actions) ")
    fun searchNpcs(@Param("searchText") searchText: String): List<NpcDefinition>
}