package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.SpotAnimationDefinition
import com.osedit.spring.repository.SpotAnimationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class SpotAnimationService(@Autowired private val spotAnimationRepository: SpotAnimationRepository) {

    @PostConstruct
    fun post() {
        with(spotAnimationRepository) {
            saveAll(Library.spotAnimations()?.asIterable() as MutableIterable<SpotAnimationDefinition>)
        }
    }

    fun spotAnimations(): List<SpotAnimationDefinition> {
        return with(spotAnimationRepository) {
            findAll()
        }
    }

    fun spotAnimationById(id: Int): Optional<SpotAnimationDefinition> {
        return with(spotAnimationRepository) {
            findById(id)
        }
    }

}