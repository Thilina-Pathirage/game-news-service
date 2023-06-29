package com.example.game_news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GameNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameNewsApplication.class, args);
        System.out.println("Hello Java Spring!");
    }
}
