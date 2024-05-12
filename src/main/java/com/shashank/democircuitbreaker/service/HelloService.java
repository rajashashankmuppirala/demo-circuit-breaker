package com.shashank.democircuitbreaker.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class HelloService {

    private final Random random = new Random();

    @CircuitBreaker(name = "helloDomainServiceException", fallbackMethod = "fallbackHelloMessage")
    public String getHelloMessageWithException(){
        if(random.nextBoolean()){
            log.info("random logic");
            throw new RuntimeException("Exception while hello from demo service");
        }
        return "hello from demo service with no exception";
    }

    @CircuitBreaker(name = "helloDomainServiceDelay", fallbackMethod = "fallbackHelloMessage")
    public String getHelloMessageWithDelay() throws InterruptedException {
        if(random.nextBoolean()){
            log.info("random logic");
            Thread.sleep(10000);
        }
        return "hello from demo service with no delay";
    }

    @RateLimiter(name = "helloDomainServiceRL", fallbackMethod = "fallbackHelloMessage")
    public String getHelloMessageWithMaxLimit() throws InterruptedException {

        log.info("getHelloMessageWithMaxLimit execution");
        return "hello from demo service with proper Max limit";
    }


    public String fallbackHelloMessage(Exception e){
        return "Fallback response due to " + e.getMessage();
    }
}
