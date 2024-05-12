package com.shashank.democircuitbreaker.controller;

import com.shashank.democircuitbreaker.service.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    private ResponseEntity<String> helloWithException(){
        return ResponseEntity.ok(helloService.getHelloMessageWithException());
    }

    @GetMapping("/hello1")
    private ResponseEntity<String> helloWithDelay() throws InterruptedException {
        return ResponseEntity.ok(helloService.getHelloMessageWithDelay());
    }

    @GetMapping("/hello2")
    private ResponseEntity<String> helloWithMaxLimit() throws InterruptedException {
        return ResponseEntity.ok(helloService.getHelloMessageWithMaxLimit());
    }
}
