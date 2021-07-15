package com.kvinod.devopsdemo;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    public String getWelcomeMessage() {
        return "Welcome to DevOps!";
    }

    public String getWelcomeMessageFor(String user) {
        return String.format("Hello %s, welcome to DevOps!", user);
    }
}
