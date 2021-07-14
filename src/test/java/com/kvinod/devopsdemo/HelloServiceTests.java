package com.kvinod.devopsdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.kvinod.devopsdemo.HelloService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloServiceTests {

    @Autowired
    HelloService service;

    @Test
    void shouldGetHelloService() {
        assertNotNull(service);
    }

    @Test
    void shouldGetMessageForInputName() {
        String expected = "Hello, Vinod!";
        String actual = service.getHelloMessage("Vinod");
        assertEquals(expected, actual);
    }

}
