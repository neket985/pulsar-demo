package com.example.pulsardemo

import com.example.pulsardemo.util.KJSONSchema
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.pulsar.client.api.Schema
import org.springframework.pulsar.core.PulsarTemplate
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class Producer(
    private val template: PulsarTemplate<Sample>,
    private val mapper: ObjectMapper
) {
    init {
        sendRandom()
    }

    fun send(entity: Sample) = template.apply {setSchema(KJSONSchema<Sample>(mapper))}.send(entity)

    fun sendRandom() = send(Sample(Random.nextInt(500), "sample"))

}