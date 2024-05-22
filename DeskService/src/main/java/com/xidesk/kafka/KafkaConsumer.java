package com.xidesk.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "file-topic", groupId = "desk-service")
    public FileMessage listen(FileMessage fileMessage) {
        return fileMessage;
    }


}
