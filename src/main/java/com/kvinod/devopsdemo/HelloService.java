package com.kvinod.devopsdemo;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HelloService {

    public String getHelloMessage(String name) {
        log.info("returning hello message for {}", name);
        return String.format("Hello, %s!", name);
    }

}
