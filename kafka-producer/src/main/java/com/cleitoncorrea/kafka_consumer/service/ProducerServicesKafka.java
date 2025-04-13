package com.cleitoncorrea.kafka_consumer.service;

import com.cleitoncorrea.kafka_consumer.model.TransactionMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ProducerServicesKafka {

    private static final Logger LOGGER = LoggerFactory.getLogger ( ProducerFactory.class );

    @Autowired
    static
    KafkaTemplate< UUID, TransactionMessage > kafkaTemplate;

    public static void send ( String topicname , UUID Key , TransactionMessage transactionMessage ){
        var future  = kafkaTemplate.send(topicname, Key,transactionMessage);
        future.whenComplete ( (sendResult, exception) ->{
            if(exception != null){
                    future.completeExceptionally ( exception );
                    LOGGER.error ( exception.getMessage () );
            }else{
                    future.complete ( sendResult );
            }

         //   LOGGER.info ( "Id do Topico: " + transactionMessage.getTransactionId ()
           // + " Transaction status Kafka Topic" + transactionMessage.getStatus ());

        });
    }
}
