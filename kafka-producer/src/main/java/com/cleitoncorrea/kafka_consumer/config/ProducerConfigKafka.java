package com.cleitoncorrea.kafka_producer.config;

import com.cleitoncorrea.kafka_producer.model.TransactionMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class ProducerConfigKafka {
    @Bean
    public ProducerFactory< UUID, TransactionMessage > producerFactory(){
        Map<String, Object> configProps = new HashMap <> ();
        configProps.put ( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092" );
        configProps.put ( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class );
        configProps.put ( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class );
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    KafkaTemplate<UUID, TransactionMessage> KafkaTemplate(){
        return new KafkaTemplate <> ( producerFactory () );
    }
}
