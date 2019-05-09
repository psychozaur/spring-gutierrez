package com.rybickim.spring;

public class HelloWorldMessage implements MessageService {
    @Override
    public String getMessage() {
        return "Hello, world";
    }
}
