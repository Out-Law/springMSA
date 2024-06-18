package ru.java.micro.spring.eurekacli.produser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;

@Service
public class KafkaProducer {

   /* private static final String TOPIC = "test_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }*/

    private static final String TOPIC_COMPENSATE = "compensate_serviceB";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "serviceB_to_serviceC", groupId = "service-c-group")
    public void consume(String message) {
        try {
            System.out.println("Super!!!!");
            // Выполнение завершающей логики
        } catch (Exception e) {
            // Если неудача, отправляем сообщение для отката
            System.out.println("C: Откат, переход к B");
            kafkaTemplate.send(TOPIC_COMPENSATE, message);
        }
    }
}