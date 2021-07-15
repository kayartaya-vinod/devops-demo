package com.kvinod.devopsdemo;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    private static final String MESSAGE = "Welcome to DevOps!";

    public String getWelcomeMessage() {
        return MESSAGE;
    }

    public String getWelcomeMessageFor(String user) {
        return String.format("Hello %s, welcome to DevOps!", user);
    }
}
