package com.xidesk.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${spring.kafka.template.receive-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        System.out.println("1: " + message);
        System.out.println("2: " + message);
        System.out.println("3: " + message);
    }

}
