package com.example.pulsardemo

import com.example.pulsardemo.util.KJSONSchema
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.apache.pulsar.client.api.PulsarClient
import org.springframework.stereotype.Service
import java.util.concurrent.Executors

@Service
class Consumer(
    private val client: PulsarClient,
    private val mapper: ObjectMapper
) {
    private val threadPool = Executors.newSingleThreadExecutor()

    @PostConstruct
    fun init() {
        val consumer = client.newConsumer(KJSONSchema<Sample>(mapper))
            .topic("test2")
            .subscriptionName("name1")
            .consumerName("consumer1")
            .subscribe()
        threadPool.execute {
            while (true) {
                val msg = consumer.receive()
                val sample = msg.value
                println("handling $sample")
            }
        }
    }
}