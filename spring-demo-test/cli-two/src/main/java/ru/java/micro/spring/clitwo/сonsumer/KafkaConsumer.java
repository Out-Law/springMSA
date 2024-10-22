package ru.java.micro.spring.clitwo.сonsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

    String bd = "";
    @KafkaListener(topics = "orders", groupId = "test-group")
    public void processOrder(ConsumerRecord<String, String> record) {
        String orderId = record.key();
        String orderDetails = record.value();
        System.out.println("Processing order: OrderId=" + orderId + ", Details=" + orderDetails);
        bd = "Processing order: OrderId=" + orderId + ", Details=" + orderDetails;
        // Логика обработки заказа, например, сохранение в базу данных инфрмацию о заказе
    }

}