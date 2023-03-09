package com.example.pulsardemo.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.pulsar.client.api.schema.SchemaDefinition
import org.apache.pulsar.client.impl.schema.AvroBaseStructSchema
import org.apache.pulsar.client.impl.schema.util.SchemaUtil
import org.apache.pulsar.common.schema.SchemaType

class KJSONSchema<T>(schemaDefinition: SchemaDefinition<T>, pojo: Class<T>, mapper: ObjectMapper) :
    AvroBaseStructSchema<T>(
        SchemaUtil.parseSchemaInfo(schemaDefinition, SchemaType.JSON)
    ) {

    companion object {
        inline operator fun <reified T> invoke(mapper: ObjectMapper): KJSONSchema<T> {
            val pojo = T::class.java
            return KJSONSchema(
                SchemaDefinition.builder<T>().withPojo(pojo).build(),
                pojo,
                mapper
            )
        }
    }

    init {
        val serializer = JsonSchemaSerializer(mapper, pojo)
        setReader(serializer.reader())
        setWriter(serializer.writer())
    }
}