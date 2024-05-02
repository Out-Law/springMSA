package ru.java.micro.spring.clitwo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sum")
public class TestController {

    @GetMapping("/number")
    public String test(
            @RequestParam("number1") int number1,
            @RequestParam("number2") int number2
    ){
        return "" + number1 + number2;
    }
}
