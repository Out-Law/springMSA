package ru.java.micro.spring.eurekacli;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test-one", groupId = "test-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

}