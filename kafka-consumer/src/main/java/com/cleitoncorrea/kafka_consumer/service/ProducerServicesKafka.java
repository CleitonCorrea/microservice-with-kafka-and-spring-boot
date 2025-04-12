package com.cleitoncorrea.kafka_consumer.service;

import com.cleitoncorrea.kafka_consumer.model.TransactionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProducerServicesKafka {

    private final Logger LOGGER = LoggerFactory.getLogger ( ProducerFactory.class );

    @Autowired
    KafkaTemplate< UUID, TransactionMessage > kafkaTemplate;

    public void send(String topicname, UUID Key, TransactionMessage transactionMessage){
        var future  = kafkaTemplate.send(topicname, Key,transactionMessage);
        future.whenComplete ( (sendResult, exception) ->{
            if(exception != null){
                    future.completeExceptionally ( exception );
                    LOGGER.error ( exception.getMessage () );
            }else{
                    future.complete ( sendResult );
            }

            LOGGER.info ( "Id do Topico: " + transactionMessage.getTransactionId ()
            + " Transaction status Kafka Topic" + transactionMessage.getStatus ());

        });
    }
}
