package com.osedit.spring.repository

import com.osedit.spring.domain.ItemDefinition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<ItemDefinition, Int> {
    @Query("SELECT def FROM ItemDefinition def WHERE name IN (:searchText) OR " +
            ":searchText IN ELEMENTS(options) OR" +
            ":searchText IN ELEMENTS(interfaceOptions) ")
    fun searchItems(@Param("searchText") searchText: String): List<ItemDefinition>
}