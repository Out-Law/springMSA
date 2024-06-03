package ru.java.micro.spring.clitwo.сonsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

//    @KafkaListener(topics = "test_topic", groupId = "cli-two-group")
//    public void listen(String message) {
//        System.out.println("Received message: " + message);
//    }

    private static final String TOPIC_OUT = "serviceB_to_serviceC";
    private static final String TOPIC_COMPENSATE = "compensate_serviceA";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "serviceA_to_serviceB", groupId = "service-b-group")
    public void consume(String message) {
        try {
            // Выполнение промежуточной логики
            // Если успех, отправляем сообщение следующему микросервису
            kafkaTemplate.send(TOPIC_OUT, message);
        } catch (Exception e) {
            // Если неудача, отправляем сообщение для отката
            kafkaTemplate.send(TOPIC_COMPENSATE, message);
        }
    }

    @KafkaListener(topics = "compensate_serviceB", groupId = "service-b-group")
    public void compensate(String message) {
        System.out.println("compensate_serviceB: Откат, передача информации A");
        kafkaTemplate.send(TOPIC_COMPENSATE, message);
    }
}