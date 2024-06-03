package com.example.clithree.controller;

import com.example.clithree.server.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/start")
    public String startTransaction(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka Topic: " + message;
    }
}