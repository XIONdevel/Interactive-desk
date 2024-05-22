package com.xidesk.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, FileMessage> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, FileMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendFile(String topic, FileMessage fileMessage) {
        kafkaTemplate.send(topic, fileMessage);
    }

}
