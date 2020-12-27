package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.SequenceDefinition
import com.osedit.spring.repository.SequenceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class SequenceService(@Autowired private val sequenceRepository: SequenceRepository) {

    @PostConstruct
    fun post() {
        with(sequenceRepository) {
            saveAll(Library.sequences()?.asIterable() as MutableIterable<SequenceDefinition>)
        }
    }

    fun sequences(): List<SequenceDefinition> {
        return with(sequenceRepository) {
            findAll()
        }
    }

    fun sequenceById(id: Int): Optional<SequenceDefinition> {
        return with(sequenceRepository) {
            findById(id)
        }
    }

}