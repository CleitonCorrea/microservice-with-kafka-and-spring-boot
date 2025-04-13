package com.cleitoncorrea.Kafka_consumer.config;

import com.cleitoncorrea.Kafka_consumer.model.TransactionMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableKafka
@Configuration
public class ConsumerConfigKafka {

    @Bean
    public ConsumerFactory< UUID, TransactionMessage > consumerFactory(){

        Map <String, Object> config = new HashMap <> ();
        config.put (ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"  );
        config.put ( ConsumerConfig.GROUP_ID_CONFIG, "group-id" );
        config.put ( ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, UUIDDeserializer.class );
        config.put ( ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class );
        config.put ( JsonDeserializer.USE_TYPE_INFO_HEADERS, false );
        config.put ( JsonDeserializer.VALUE_DEFAULT_TYPE, "com.cleitoncorrea.Kafka_consumer.model.TransactionMessage" );
        return new DefaultKafkaConsumerFactory <> ( config );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID, TransactionMessage> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<UUID, TransactionMessage>
                factory = new ConcurrentKafkaListenerContainerFactory<> ();
        factory.setConsumerFactory (consumerFactory ()  );
        return factory;
    }
}
