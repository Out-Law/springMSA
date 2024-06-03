package ru.java.micro.spring.eurekacli.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.java.micro.spring.eurekacli.produser.KafkaProducer;

@RestController
@RequestMapping("/kafka")
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;

    /*@GetMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka Topic: " + message;
    }*/

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
