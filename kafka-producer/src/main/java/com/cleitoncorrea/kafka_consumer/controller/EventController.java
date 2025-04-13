package com.cleitoncorrea.kafka_consumer.controller;

import com.cleitoncorrea.kafka_consumer.model.TransactionMessage;
import com.cleitoncorrea.kafka_consumer.service.ProducerServicesKafka;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
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
