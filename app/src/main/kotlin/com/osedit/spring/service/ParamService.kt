package com.osedit.spring.service

import com.osedit.cache.Library
import com.osedit.spring.domain.ParamDefinition
import com.osedit.spring.repository.ParamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class ParamService(@Autowired private val paramRepository: ParamRepository) {

    @PostConstruct
    fun post() {
        with(paramRepository) {
            saveAll(Library.params()?.asIterable() as MutableIterable<ParamDefinition>)
        }
    }

    fun params(): List<ParamDefinition> {
        return with(paramRepository) {
            findAll()
        }
    }

    fun paramById(id: Int): Optional<ParamDefinition> {
        return with(paramRepository) {
            findById(id)
        }
    }

}