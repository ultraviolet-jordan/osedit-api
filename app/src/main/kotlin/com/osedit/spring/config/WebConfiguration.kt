package com.osedit.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
open class WebConfiguration {
    companion object {
        @Bean
        fun corsConfigurer(): WebMvcConfigurer {
            return object : WebMvcConfigurerAdapter() {
                override fun addCorsMappings(registry: CorsRegistry) {
                    registry.addMapping("/**")
                }
            }
        }
    }

}