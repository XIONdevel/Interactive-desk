package com.xidesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
todo: remove in future
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TestController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<Boolean> sendKafkaMessage(@RequestBody String message) {
        kafkaTemplate.send("desk-to-customer", message);
        return ResponseEntity.ok(true);
    }



}
