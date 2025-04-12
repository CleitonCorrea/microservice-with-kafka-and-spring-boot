package com.cleitoncorrea.kafka_producer.controller;

import com.cleitoncorrea.kafka_producer.model.TransactionMessage;
import com.cleitoncorrea.kafka_producer.service.ProducerServicesKafka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class EventController {

    @Autowired
    ProducerServicesKafka producerServicesKafka;

    @PostMapping("event")
    ResponseEntity<String> event( @RequestBody TransactionMessage transactionMessage ){
        UUID uuid = UUID.randomUUID ();

        ProducerServicesKafka.send("transaction-topic", uuid, transactionMessage);

        return ResponseEntity.ok ("Sent");

    }
}
