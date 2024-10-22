package ru.java.micro.spring.eurekacli.produser;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("test-topic", message);
        //kafkaTemplate.send("kafka-topic",  "KeyKey", "Hello, Kafka!");
        //kafkaTemplate.send("__consumer_offsets",  "Hello, Kafka!");
        System.out.println("Message sent to topic " + ": " + message);

    }

    public void sendOrder() {
        // Отправляем сообщение в топик "orders" с ключом - номер заказа и значением - информация о заказе
        int a = 0;
        int b = 50;
        while (b>0){
            a++;
            b--;
            kafkaTemplate.send("orders", "order" + a, "orderDetails" + a);
        }
        //System.out.println("Order sent: OrderId=" + orderId + ", Details=" + orderDetails);
    }

}