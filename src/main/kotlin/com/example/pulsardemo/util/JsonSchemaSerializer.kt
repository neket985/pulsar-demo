package com.example.pulsardemo.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.pulsar.client.api.schema.SchemaReader
import org.apache.pulsar.client.api.schema.SchemaWriter
import java.io.InputStream

class JsonSchemaSerializer<T>(private val mapper: ObjectMapper, private val clazz: Class<T>) {

    companion object {
        inline operator fun <reified T> invoke(mapper: ObjectMapper) = JsonSchemaSerializer(mapper, T::class.java)
    }

    fun reader(): SchemaReader<T> = JsonReader(mapper, clazz)

    fun writer(): SchemaWriter<T> = JsonWriter(mapper)


    class JsonReader<RT>(private val mapper: ObjectMapper, private val clazz: Class<RT>) : SchemaReader<RT> {
        override fun read(bytes: ByteArray?, offset: Int, length: Int): RT? =
            bytes?.let { mapper.readValue(it, offset, length, clazz) }

        override fun read(inputStream: InputStream?): RT? = inputStream?.let { mapper.readValue(it, clazz) }
    }

    class JsonWriter<WT>(private val mapper: ObjectMapper) : SchemaWriter<WT> {
        override fun write(message: WT): ByteArray = mapper.writeValueAsBytes(message)
    }
}