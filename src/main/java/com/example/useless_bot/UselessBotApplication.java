package com.example.useless_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UselessBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(UselessBotApplication.class, args);
    }

}
