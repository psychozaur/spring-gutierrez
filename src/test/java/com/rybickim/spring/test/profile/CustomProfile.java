package com.rybickim.spring.test.profile;

import org.springframework.test.annotation.ProfileValueSource;

public class CustomProfile implements ProfileValueSource {

    @Override
    public String get(String key) {
        if(key.equals("environment")) return "dev";
        else if(key.equals("os.name")) return "Unix";
        return null;
    }
}
