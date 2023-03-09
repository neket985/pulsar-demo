package com.example.pulsardemo

import com.example.pulsardemo.util.KJSONSchema
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema
import org.apache.pulsar.client.impl.schema.JSONSchema
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.pulsar.core.ProducerBuilderCustomizer
import org.springframework.pulsar.core.PulsarTopic

@Configuration
class TopicsConfig {

    @Bean
    fun topic() = PulsarTopic("test2", 0)

    @Bean
    fun mapper() = jacksonObjectMapper()

}