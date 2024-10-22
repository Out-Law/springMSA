package ru.java.micro.spring.eurekacli.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.micro.spring.eurekacli.produser.KafkaProducer;

import java.util.Map;

@RestController
@RequestMapping("/kafka")
public class MessageController {

    private final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent");
    }

    @PostMapping("/orders/create")
    public ResponseEntity<String> createOrder() {
        kafkaProducer.sendOrder();
        return ResponseEntity.ok("Order created and sent: ");
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
