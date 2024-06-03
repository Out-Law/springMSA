package com.example.clithree.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "serviceA_to_serviceB";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

    @KafkaListener(topics = "compensate_serviceA", groupId = "service-a-group")
    public void compensate(String message) {
       System.out.println("Откат, конец!!!!");
    }
}
