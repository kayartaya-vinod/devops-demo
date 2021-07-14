package com.kvinod.devopsdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    HelloService service;

    @GetMapping
    public String sayHello(@RequestParam(name = "name", defaultValue = "friend", required = false) String name) {
        log.info("saying hello for {}", name);
        return service.getHelloMessage(name);
    }
}
