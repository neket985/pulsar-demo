package com.example.pulsardemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PulsarDemoApplication

fun main(args: Array<String>) {
    runApplication<PulsarDemoApplication>(*args)
}
